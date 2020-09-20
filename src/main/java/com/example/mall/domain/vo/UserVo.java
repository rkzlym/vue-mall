package com.example.mall.domain.vo;

import com.example.mall.domain.constans.RoleConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo implements Serializable {

    /* 用户ID */
    private Long id;

    /* 用户名 */
    @NotEmpty(message = "用户名不能为空")
    @Length(min = 4, max = 32, message = "用户名长度必须在4-32位")
    private String username;

    /* 手机号 */
    @Pattern(regexp = "^(1[3-9])\\d{9}$", message = "手机号格式有误")
    private String phone;

    /* 邮箱 */
    @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "邮箱号格式有误")
    private String email;

    /* 角色 */
    private String role = RoleConstants.ROLE_NORMAL;

    /* 状态 */
    private Boolean state = true;

    public UserVo (Long id, String username){
        this.id = id;
        this.username = username;
    }
}
