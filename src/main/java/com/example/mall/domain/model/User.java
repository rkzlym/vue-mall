package com.example.mall.domain.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@TableName("tb_user")
@Data
@Accessors(chain = true)
public class User implements Serializable {

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

    /* 用户名 */
    @NotEmpty(message = "用户名不能为空")
    @Length(min = 4, max = 32, message = "用户名长度必须在4-32位")
    private String username;

    /* 密码 */
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, max = 32, message = "密码长度必须在4-32位")
    private String password;

    /* 手机号 */
    @NotEmpty(message = "手机号不能为空")
    @Pattern(regexp = "^(1[3-9])\\d{9}$", message = "手机号格式有误")
    private String phone;

    /* 邮箱 */
    private String email;

}
