package com.jensuper.prc.entity;

import java.math.BigDecimal;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2020/11/02
 */
public class Test {

    public static void main(String[] args) {

        UserDTO userDTO = new UserDTO().setId(1L).setName("李白");

        User user = userDTO.convertToUser();

        UserDTO userDTORet = userDTO.convertFor(user);


    }
}
