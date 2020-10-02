package com.example.mall.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RoleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /* 主键 */
    private Long id;

    /* 角色名称 */
    private String roleName;

    /* 角色描述 */
    private String roleDesc;

    /* 角色对应的父权限ID */
    @JsonIgnore
    private List<Long> authParentIds;

    /* 权限列表 */
    private List<AuthVo> authList;
}
