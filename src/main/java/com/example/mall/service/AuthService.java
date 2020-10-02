package com.example.mall.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mall.config.JwtProperties;
import com.example.mall.domain.exception.ExceptionEnum;
import com.example.mall.domain.exception.SystemException;
import com.example.mall.domain.model.Auth;
import com.example.mall.domain.model.User;
import com.example.mall.domain.vo.AuthVo;
import com.example.mall.domain.vo.UserVo;
import com.example.mall.mapper.AuthMapper;
import com.example.mall.utils.JwtUtils;
import com.example.mall.utils.SuperBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AuthService extends ServiceImpl<AuthMapper, Auth> {

    private final AuthMapper authMapper;

    private final UserService userService;

    private final JwtProperties prop;

    public AuthService(AuthMapper authMapper, UserService userService, JwtProperties prop) {
        this.authMapper = authMapper;
        this.userService = userService;
        this.prop = prop;
    }

    public List<AuthVo> select(List<Long> authIds){
        return SuperBeanUtils.copyListProperties(authMapper.selectBatchIds(authIds), AuthVo::new);
    }

    public List<Auth> select(Long parentAuthId){
        return authMapper.selectList(Wrappers.<Auth>lambdaQuery().eq(Auth::getParentId, parentAuthId));
    }

    public String login(String username, String password) {
        UserVo user = userService.select(username, password);
        try {
            return JwtUtils.generateToken(user, this.prop.getPrivateKey(), this.prop.getExpire());
        } catch (Exception e) {
            log.error("[授权中心] 生成Token失败 用户名: {}", username, e);
            throw new SystemException(ExceptionEnum.INVALID_USERNAME_PASSWORD);
        }
    }
}
