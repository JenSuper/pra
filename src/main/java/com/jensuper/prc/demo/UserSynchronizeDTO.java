package com.jensuper.prc.demo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author yulon
 * @version V1.0
 * @className: UserDTO
 * @date 2019/7/213:33
 */
@Data
@Accessors(chain = true)
@NotNull
public class UserSynchronizeDTO implements Serializable {

    /**
     * 主键ID
     */
    private Long id;
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String name;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 用户状态/是否有效(1是0否)
     */
    private Boolean status;
    /**
     * 密码
     */
    private String password;
    /**
     * 盐值
     */
    private String salt;
}
