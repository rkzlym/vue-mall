package com.example.mall.controller;

import com.example.mall.domain.common.CommonResult;
import com.example.mall.domain.model.Role;
import com.example.mall.domain.vo.RoleVo;
import com.example.mall.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("save")
    public CommonResult<Void> save(@RequestBody Role role){
        roleService.saveOrUpdate(role);
        return CommonResult.success("操作成功");
    }

    @GetMapping
    public CommonResult<List<RoleVo>> list(){
        return CommonResult.success(roleService.selectAll());
    }

    @DeleteMapping("{roleId}/auth/{authId}")
    public CommonResult<RoleVo> deleteAuth(@PathVariable("roleId") Long roleId, @PathVariable("authId") String authId){
        return CommonResult.success(roleService.deleteAuth(roleId, authId));
    }
}
