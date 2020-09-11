package com.example.mall.domain.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@TableName("tb_menu")
@Data
@Accessors(chain = true)
public class Menu implements Serializable {

    /* 主键 */
    @TableId
    private Long id;

    /* 创建时间 */
    private Date createTime;

    /* 修改时间 */
    private Date updateTime;

    /* 版本号 */
    private Long version;

    /* 是否已删除 */
    private Boolean isDelete;

    /* 菜单名称 */
    private String name;

    /* 菜单地址 */
    private String url;

    /* 父菜单ID */
    private Long parentId;
}
