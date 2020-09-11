package com.example.mall.controller;

import com.example.mall.config.JwtProperties;
import com.example.mall.service.AuthService;
import com.example.mall.utils.CookieUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("auth")
public class AuthController {
    private final AuthService authService;

    private final JwtProperties prop;

    public AuthController(AuthService authService, JwtProperties prop) {
        this.authService = authService;
        this.prop = prop;
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestParam("username") String username,
                                        @RequestParam("password") String password,
                                        HttpServletRequest request,
                                        HttpServletResponse response){
        String token = authService.login(username, password);
        CookieUtils.setCookie(request, response, prop.getCookieName(), token, prop.getExpire() * 60);
        return ResponseEntity.ok(token);
    }

    @GetMapping("hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello World");
    }
}
