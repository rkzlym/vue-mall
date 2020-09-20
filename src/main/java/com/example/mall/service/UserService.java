package com.example.mall.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mall.domain.exception.ExceptionEnum;
import com.example.mall.domain.exception.SystemException;
import com.example.mall.domain.vo.UserVo;
import com.example.mall.mapper.UserMapper;
import com.example.mall.domain.model.User;
import com.example.mall.utils.SuperBeanUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Page<UserVo> select(String keyword, Integer pageNum, Integer pageSize){
        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize),
                Wrappers.<User>lambdaQuery().like(User::getUsername, keyword));
        List<UserVo> userVos = SuperBeanUtils.copyListProperties(userPage.getRecords(), UserVo::new);
        Page<UserVo> userVoPage = new Page<>();
        BeanUtils.copyProperties(userPage, userVoPage);
        userVoPage.setRecords(userVos);
        return userVoPage;
    }

    public UserVo select(String username, String password) {
        User user = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getUsername, username));
        if(user == null || !StringUtils.equals(DigestUtils.md5Hex(password), user.getPassword()))
            throw new SystemException(ExceptionEnum.INVALID_USERNAME_PASSWORD);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, new UserVo());
        return userVo;
    }

    public UserVo select(Long id){
        User user = userMapper.selectById(id);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        return userVo;
    }

    public void updateState(Long id, Boolean state){
        User entity = new User();
        entity.setId(id);
        entity.setState(state);
        userMapper.updateById(entity);
    }

    public void insert(User user) {
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setVersion(0);
        user.setIsDelete(false);
        userMapper.insert(user);
    }

    public void update(UserVo userVo){
        User user = new User();
        BeanUtils.copyProperties(userVo, user);
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateById(user);
    }

    public void delete(Long id) {
        userMapper.deleteById(id);
    }
}
