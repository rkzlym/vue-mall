package com.example.mall.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.mall.domain.model.Menu;
import com.example.mall.domain.vo.MenuVo;
import com.example.mall.mapper.MenuMapper;
import com.example.mall.utils.SuperBeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    private final MenuMapper menuMapper;

    public MenuService(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    public List<MenuVo> select() {
        List<Menu> menus = menuMapper.selectList(null);
        return buildMenus(menus);
    }

    public List<MenuVo> select(List<Long> authIds){
        List<Menu> menus = menuMapper.selectList(Wrappers.<Menu>lambdaQuery().in(Menu::getAuthId, authIds));
        return buildMenus(menus);
    }

    private List<MenuVo> buildMenus(List<Menu> menus){
        List<MenuVo> menuVos = SuperBeanUtils.copyListProperties(menus, MenuVo::new);
        List<MenuVo> menuParents = new ArrayList<>();

        // 先找出所有的一级菜单
        for (MenuVo menu : menuVos)
            if (menu.getParentId() == null)
                menuParents.add(menu);

        // 递归设置子菜单
        for (MenuVo menuVo : menuParents)
            menuVo.setSubMenus(getSubMenus(menuVo.getId(), menuVos));

        return menuParents;
    }

    private List<MenuVo> getSubMenus(Long id, List<MenuVo> menus) {
        List<MenuVo> subMenus = new ArrayList<>();

        // 遍历所有节点，将父菜单id与传过来的id比较，取得父菜单下的所有子菜单
        for (MenuVo menu : menus)
            if (menu.getParentId() != null && menu.getParentId().equals(id))
                subMenus.add(menu);

        // 递归遍历子菜单，没有url的表示还有子菜单
        for (MenuVo menu : subMenus)
            if (StringUtils.isBlank(menu.getUrl()))
                menu.setSubMenus(getSubMenus(menu.getId(), menus));

        // 如果子菜单个数为0 返回null
        if (subMenus.size() == 0) return null;
        return subMenus;
    }
}
