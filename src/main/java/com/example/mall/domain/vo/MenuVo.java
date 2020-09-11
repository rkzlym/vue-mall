package com.example.mall.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MenuVo implements Serializable {

    /* 主键 */
    private Long id;

    /* 菜单名称 */
    private String name;

    /* 菜单路径 */
    private String url;

    /* 父菜单ID */
    private Long parentId;

    /* 子菜单 */
    private List<MenuVo> subMenus;
}
