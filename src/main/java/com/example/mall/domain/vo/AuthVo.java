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
public class AuthVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /* 主键 */
    private Long id;

    /* 权限名称 */
    private String name;

    /* 权限等级 */
    private Integer level;

    /* 是否是最后的子权限 */
    @JsonIgnore
    private Boolean isLast;

    /* 父权限ID */
    @JsonIgnore
    private Long parentId;

    /* 子权限列表 */
    private List<AuthVo> children;
}
