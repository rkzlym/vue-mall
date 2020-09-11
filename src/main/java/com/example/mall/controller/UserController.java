package com.example.mall.controller;

import com.example.mall.domain.model.User;
import com.example.mall.service.UserService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("check")
    public ResponseEntity<User> check(@RequestParam("username") String username,
                                      @RequestParam("password") String password){
        return ResponseEntity.ok(userService.queryUser(username, password));
    }

    @PostMapping("register")
    public ResponseEntity<User> register(@Valid @RequestBody User user, BindingResult result){
        if(result.hasErrors())
            throw new RuntimeException(result.getFieldErrors()
                    .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining("/")));
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
