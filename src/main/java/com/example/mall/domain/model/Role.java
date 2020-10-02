package com.example.mall.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@TableName("tb_role")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Role implements Serializable {

    private static final long serialVersionUID=1L;

    /* 主键 */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /* 创建时间 */
    private LocalDateTime createTime;

    /* 修改时间 */
    private LocalDateTime updateTime;

    /* 版本号 */
    private Integer version;

    /* 是否已删除 */
    private Boolean isDelete;

    /* 角色对应的父权限ID */
    private String authParentIds;

    /* 权限表ID列表 */
    private String authIds;

    /* 角色名称 */
    private String roleName;

    /* 角色描述 */
    private String roleDesc;
}
