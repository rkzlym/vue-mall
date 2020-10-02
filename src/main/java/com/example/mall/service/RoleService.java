package com.example.mall.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mall.domain.model.Auth;
import com.example.mall.domain.model.Role;
import com.example.mall.domain.vo.AuthVo;
import com.example.mall.domain.vo.RoleVo;
import com.example.mall.mapper.RoleMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> {

    private final RoleMapper roleMapper;

    private final AuthService authService;

    public RoleService(RoleMapper roleMapper, AuthService authService) {
        this.roleMapper = roleMapper;
        this.authService = authService;
    }

    public List<RoleVo> selectAll() {
        List<Role> roles = roleMapper.selectList(null);
        List<RoleVo> roleVos = new ArrayList<>();
        for (Role role : roles)
            roleVos.add(select(role.getId()));
        return roleVos;
    }

    public RoleVo select(Long id) {
        Role role = roleMapper.selectOne(Wrappers.<Role>lambdaQuery().eq(Role::getId, id));
        // 取出对应角色下所有的AuthVo
        String[] arr = role.getAuthIds().split(",");
        List<Long> authIds = Arrays.stream(arr).mapToLong(Long::parseLong).boxed().collect(Collectors.toList());
        List<AuthVo> authVos = authService.select(new ArrayList<>(authIds));
        // 取到最顶层的AuthId 递归调用形成新的数据结构
        List<AuthVo> authParents = new ArrayList<>();
        for (AuthVo authVo : authVos)
            if (authVo.getLevel() == 1)
                authParents.add(authVo);
        for (AuthVo authParent : authParents) {
            authParent.setChildren(buildChildren(authParent.getId(), authVos));
        }
        // 构建角色数据结构 每个角色会对应多个父权限
        RoleVo roleVo = new RoleVo();
        BeanUtils.copyProperties(role, roleVo);
        List<Long> authParentIds = Arrays.stream(role.getAuthParentIds().split(","))
                .mapToLong(Long::parseLong).boxed().collect(Collectors.toList());
        roleVo.setAuthParentIds(authParentIds);
        buildRole(roleVo, authParents);
        return roleVo;
    }

    private List<AuthVo> buildChildren(Long id, List<AuthVo> authVos) {
        List<AuthVo> children = new ArrayList<>();
        // 遍历所有节点，将父菜单id与传过来的id比较，取得父菜单下的所有子菜单
        for (AuthVo authVo : authVos)
            if (authVo.getParentId() != null && authVo.getParentId().equals(id))
                children.add(authVo);

        // 递归遍历子权限，如果子权限未标有isLast，表示还有子权限
        for (AuthVo child : children)
            if (!child.getIsLast())
                child.setChildren(buildChildren(child.getId(), authVos));

        // 如果子权限的个数为0 返回null
        if (children.size() == 0) return null;
        return children;
    }

    private void buildRole(RoleVo roleVo, List<AuthVo> authParents){
        List<AuthVo> authList = new ArrayList<>();
        // 遍历角色下的所有父权限ID 与所有父权限作对比 如果相同则使角色拥有该权限
        for (Long authParentId : roleVo.getAuthParentIds())
            for (AuthVo authParent : authParents)
                if (authParent.getId().equals(authParentId))
                    authList.add(authParent);
        roleVo.setAuthList(authList);
    }

    public RoleVo deleteAuth(Long roleId, String authId) {
        Role role = roleMapper.selectById(roleId);
        // 先递归删除子权限
        deleteSubAuth(role, Long.parseLong(authId));
        // 最终删除选中权限
        List<String> authIds = Arrays.stream(role.getAuthIds().split(",")).collect(Collectors.toList());
        authIds.removeIf(id -> StringUtils.equals(id, authId));
        role.setAuthIds(StringUtils.join(authIds.toArray(), ","));
        roleMapper.updateById(role);
        return select(roleId);
    }

    private void deleteSubAuth(Role role, Long parentAuthId){
        List<Long> authIds = Arrays.stream(role.getAuthIds().split(","))
                .mapToLong(Long::parseLong).boxed().collect(Collectors.toList());
        List<Auth> authList = authService.select(parentAuthId);
        Iterator<Long> iter = authIds.iterator();

        while (iter.hasNext()) {
            Long next = iter.next();
            for (Auth auth : authList)
                if (next.equals(auth.getId()))
                    iter.remove();
        }

        role.setAuthIds(StringUtils.join(authIds.toArray(), ","));

        // 遍历子权限 如果是最后一级权限则不递归删除 否则递归删除子权限
        for (Auth auth : authList) {
            if (auth.getIsLast()) continue;
            deleteSubAuth(role, auth.getId());
        }
    }
}
