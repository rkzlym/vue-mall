package com.example.mall.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@TableName("tb_menu")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Menu implements Serializable {

    /* 主键 */
    @TableId(type = IdType.AUTO)
    private Long id;

    /* 创建时间 */
    private LocalDateTime createTime;

    /* 修改时间 */
    private LocalDateTime updateTime;

    /* 版本号 */
    private Integer version;

    /* 是否已删除 */
    private Boolean isDelete;

    /* 菜单名称 */
    private String name;

    /* 菜单地址 */
    private String url;

    /* 父菜单ID */
    private Long parentId;

    /* 权限表ID */
    private Long authId;
}
