package com.jensuper.prc.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * @author jichao
 * @version V1.0
 * @description: 简单shiro授权
 * @date 2019/08/08
 */
public class ShiroSimpleDemo {

    private static String username = "jensuper";
    private static String password = "123456";
    private static String role = "admin";

    private static SimpleAccountRealm realm = new SimpleAccountRealm();

    @Before
    public void setRealm(){
        realm.addAccount(username, password,role);
    }

    @Test
    public void auth(){
        // 构建SecurityManager环境
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(realm);

        // 主题提交认证请求
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        // 登陆
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        subject.login(token);

        System.out.println("登陆结果:"+subject.isAuthenticated());

        // 测试授权
        subject.checkRole(role);

        // 退出
        subject.logout();
        System.out.println("登陆结果:"+subject.isAuthenticated());
    }

}
