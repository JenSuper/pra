package com.jensuper.prc.entity;

import com.google.common.base.Converter;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * @author jichao
 * @version V1.0
 * @description: 对象拷贝
 * @date 2020/11/02
 */
@Data
@Accessors(chain = true)
public class UserDTO {

    private Long id;

    private String name;

    /**
     * UserDTO ==> User
     * @return
     */
    public User convertToUser() {
        UserDTOConvert userDTOConvert = new UserDTOConvert();
        User user = userDTOConvert.convert(this);
        return user;
    }

    /**
     * User ==> UserDTO
     * @param user
     * @return
     */
    public UserDTO convertFor(User user) {
        UserDTOConvert userDTOConvert = new UserDTOConvert();
        UserDTO userDTO = userDTOConvert.reverse().convert(user);
        return userDTO;
    }


    private static class UserDTOConvert extends Converter<UserDTO, User> {

        @Override
        protected User doForward(UserDTO userDTO) {
            User user = new User();
            BeanUtils.copyProperties(userDTO, user);
            return user;
        }

        @Override
        protected UserDTO doBackward(User user) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user, userDTO);
            return userDTO;
        }
    }
}
