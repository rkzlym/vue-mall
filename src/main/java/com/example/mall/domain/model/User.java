package com.example.mall.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.mall.domain.constans.RoleConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@TableName("tb_user")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

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

    /* 用户名 */
    @NotEmpty(message = "用户名不能为空")
    @Length(min = 4, max = 32, message = "用户名长度必须在4-32位")
    private String username;

    /* 密码 */
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, max = 32, message = "密码长度必须在4-32位")
    private String password;

    /* 手机号 */
    @Pattern(regexp = "^(1[3-9])\\d{9}$", message = "手机号格式有误")
    private String phone;

    /* 邮箱 */
    @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "邮箱号格式有误")
    private String email;

    /* 状态 */
    private Boolean state = true;

    /* 角色表ID */
    private Long roleId;
}
