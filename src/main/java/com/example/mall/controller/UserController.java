package com.example.mall.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mall.domain.common.CommonResult;
import com.example.mall.domain.model.User;
import com.example.mall.domain.vo.UserVo;
import com.example.mall.service.UserService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("list")
    public ResponseEntity<Page<UserVo>> list(String keyword, Integer pageNum, Integer pageSize){
        return ResponseEntity.ok(userService.select(keyword, pageNum, pageSize));
    }

    @PutMapping("{id}/state/{state}")
    public ResponseEntity<Void> updateState(@PathVariable("id") Long id, @PathVariable("state") Boolean state){
        userService.updateState(id, state);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public CommonResult<UserVo> getUser(@PathVariable("id") Long id){
        return CommonResult.success(userService.select(id));
    }

    @GetMapping("check")
    public ResponseEntity<UserVo> check(@RequestParam("username") String username, @RequestParam("password") String password){
        return ResponseEntity.ok(userService.select(username, password));
    }

    @PostMapping("register")
    public ResponseEntity<Void> register(@Valid @RequestBody User user, BindingResult result){
        if(result.hasErrors())
            throw new RuntimeException(result.getFieldErrors()
                    .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining("/")));
        userService.insert(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("edit")
    public CommonResult<Void> edit(@Valid @RequestBody UserVo user, BindingResult result){
        if(result.hasErrors())
            throw new RuntimeException(result.getFieldErrors()
                    .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining("/")));
        userService.update(user);
        return CommonResult.success("操作成功");
    }

    @DeleteMapping("{id}")
    public CommonResult<Void> delete(@PathVariable("id") Long id){
        userService.delete(id);
        return CommonResult.success("操作成功");
    }
}
