package com.example.mall.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mall.domain.exception.ExceptionEnum;
import com.example.mall.domain.exception.SystemException;
import com.example.mall.mapper.UserMapper;
import com.example.mall.domain.model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User queryUser(String username, String password) {
        User user = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getUsername, username));
        if(user == null || !StringUtils.equals(DigestUtils.md5Hex(password), user.getPassword()))
            throw new SystemException(ExceptionEnum.INVALID_USERNAME_PASSWORD);
        return user;
    }

    public void createUser(User user) {
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setVersion(0L);
        user.setIsDelete(false);
        userMapper.insert(user);
    }
}
