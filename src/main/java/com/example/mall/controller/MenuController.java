package com.example.mall.controller;

import com.example.mall.domain.common.CommonResult;
import com.example.mall.domain.vo.MenuVo;
import com.example.mall.service.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("list")
    public CommonResult<List<MenuVo>> list(){
        return CommonResult.success(menuService.select());
    }

    @PostMapping("list")
    public CommonResult<List<MenuVo>> list(@RequestParam("roleIds") List<Long> roleIds){
        return CommonResult.success(menuService.select());
    }
}
