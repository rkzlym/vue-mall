package com.example.mall.service;

import com.example.mall.config.JwtProperties;
import com.example.mall.domain.exception.ExceptionEnum;
import com.example.mall.domain.exception.SystemException;
import com.example.mall.domain.model.User;
import com.example.mall.domain.vo.UserVo;
import com.example.mall.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {

    private final UserService userService;

    private final JwtProperties prop;

    public AuthService(UserService userService, JwtProperties prop) {
        this.userService = userService;
        this.prop = prop;
    }

    public String login(String username, String password) {
        User user = userService.queryUser(username, password);
        if (user == null) throw new SystemException(ExceptionEnum.INVALID_USERNAME_PASSWORD);

        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setUsername(user.getUsername());
        try {
            return JwtUtils.generateToken(userVo, this.prop.getPrivateKey(), this.prop.getExpire());
        } catch (Exception e) {
            log.error("[授权中心] 生成Token失败 用户名: {}", username, e);
            throw new SystemException(ExceptionEnum.INVALID_USERNAME_PASSWORD);
        }
    }
}
