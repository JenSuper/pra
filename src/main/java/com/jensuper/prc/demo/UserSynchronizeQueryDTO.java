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
public class UserSynchronizeQueryDTO implements Serializable {
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String name;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;


}
