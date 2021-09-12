package com.jensuper.prc.juc.threadlocal;

import lombok.Data;
import org.springframework.util.StopWatch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jichao
 * @version V1.0
 * @description: 使用ThreadLocal 方式  利用ThreadLocal，给每个线程分配自己的dateFormat对象，保证了线程安全，高效利用内存
 * @date 2020/09/16
 */
public class ThreadLocalNormalUse4 {


    public static void main(String[] args) {
        UserSerivce userSerivce = new UserSerivce();
        userSerivce.process();
    }
}

class UserSerivce {
    public void process() {
        User user = new User();
        user.setName("大圣神威");
        UserHoderUtil.userContextHolder.set(user);
        UserSerivce2 userSerivce2 = new UserSerivce2();
        userSerivce2.process();
    }
}

class UserSerivce2 {
    public void process() {
        User user = UserHoderUtil.userContextHolder.get();
        System.out.println("UserSerivce2 ... userName = " + user.getName());
        UserSerivce3 userSerivce3 = new UserSerivce3();
        userSerivce3.process();
    }
}

class UserSerivce3 {
    public void process() {
        User user = UserHoderUtil.userContextHolder.get();
        System.out.println("UserSerivce3 ... userName = " + user.getName());
    }
}

class UserHoderUtil {
    public static ThreadLocal<User> userContextHolder = new ThreadLocal<>();
}

@Data
class User {
    private String name;
}
