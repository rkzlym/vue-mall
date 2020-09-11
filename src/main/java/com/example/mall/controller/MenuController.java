package com.example.mall.controller;

import com.example.mall.domain.vo.MenuVo;
import com.example.mall.service.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("list")
    public ResponseEntity<List<MenuVo>> list(){
        return ResponseEntity.ok(menuService.select());
    }
}
