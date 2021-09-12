package com.jensuper.prc.db;

import java.sql.*;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2020/03/03
 */
public class SelectData {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        final String url = "jdbc:mysql://172.24.103.102/sell?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        final String name = "com.mysql.cj.jdbc.Driver";
        final String user = "root";
        final String password = "123456";
        Connection conn = null;
        //指定连接类型
        Class.forName(name);
        //获取连接
        conn = DriverManager.getConnection(url, user, password);

        String sql = "select * from data_set where id > 100000";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i < columnCount+1; i++) {
            System.out.println(metaData.getColumnLabel(i));
        }
    }
}
