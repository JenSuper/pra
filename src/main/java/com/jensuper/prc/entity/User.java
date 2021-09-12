package com.jensuper.prc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2020/11/02
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String name;


    private void printPrivate() {
        System.out.println("printPrivate" + name);
    }

    public void printPublic() {
        System.out.println("printPublic" + name);
    }

    public static void main(String[] args) {
        User test = User.builder().id(1L).name("TEST").build();
        User test1 = User.builder().id(1L).name("TEST").build();
        Set<User> set = new HashSet<>();
        set.add(test);
        set.add(test1);
        System.out.println();
    }
}
