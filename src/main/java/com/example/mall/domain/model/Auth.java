package com.example.mall.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("tb_auth")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Auth implements Serializable {

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

    /* 权限名称 */
    private String name;
}
