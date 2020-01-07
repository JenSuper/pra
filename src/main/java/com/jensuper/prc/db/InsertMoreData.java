package com.jensuper.prc.db;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/10/18
 */
public class InsertMoreData {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        final String url = "jdbc:mysql://127.0.0.1/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        final String name = "com.mysql.cj.jdbc.Driver";
        final String user = "root";
        final String password = "root";
//        final String url = "jdbc:mysql://172.28.101.48:3306/test?useSSL=false&serverTimezone=GMT%2B8&tinyInt1isBit=false&allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8";
//        final String name = "com.mysql.cj.jdbc.Driver";
//        final String user = "root";
//        final String password = "123456";
        Connection conn = null;
        //指定连接类型
        Class.forName(name);
        //获取连接
        conn = DriverManager.getConnection(url, user, password);
        if (conn != null) {
            System.out.println("获取连接成功");
            insert(conn);
//            insertLocal(conn);
        } else {
            System.out.println("获取连接失败");
        }

    }

    public static void insert(Connection conn) {
        // 开始时间
        Long begin = System.currentTimeMillis();
        // sql前缀
        String prefix = "INSERT INTO account (name, age) VALUES ";
        try {
            // 保存sql后缀
            StringBuffer suffix = new StringBuffer();
            // 设置事务为非自动提交
            conn.setAutoCommit(false);
            // 比起st，pst会更好些
            //准备执行语句
            PreparedStatement pst =  conn.prepareStatement(" ");
            // 外层循环，总提交事务次数
            for (int i = 1; i <= 100; i++) {
                suffix = new StringBuffer();
                // 第j次提交步长
                for (int j = 1; j <= 10000; j++) {
                    // 构建SQL后缀
                    suffix.append("('" + "cxx" + j + "'," + j + "),");
                }
                // 构建完整SQL
                String sql = prefix + suffix.substring(0, suffix.length() - 1);
                // 添加执行SQL
                pst.addBatch(sql);
                // 执行操作
                pst.executeBatch();
                // 提交事务
                conn.commit();
                // 清空上一次添加的数据
                suffix = new StringBuffer();
            }
            // 关闭连接
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 结束时间
        Long end = System.currentTimeMillis();
        // 耗时
        System.out.println("100万条数据插入花费时间 : " + (end - begin) / 1000 + " s");
        System.out.println("插入完成");
    }

    public static void insertLocal(Connection conn){
        String createSql = "CREATE TABLE `account` (" +
                "  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键'," +
                "  `name` varchar(100) NOT NULL COMMENT '名称'," +
                "  `age` bigint(20) unsigned DEFAULT NULL COMMENT '年龄'," +
                "  PRIMARY KEY (`id`)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户';";
        String insertSql = "insert into account(name,age) values('小明',123);insert into account(name,age) values('小明2',122)";
        try {
            PreparedStatement statement = conn.prepareStatement(createSql);
            statement.execute();
            // insert
            PreparedStatement preparedStatement = conn.prepareStatement(insertSql);
            preparedStatement.executeBatch();
            // 必须使用这个,否则数据不入库
            preparedStatement.execute();
            statement.close();
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
