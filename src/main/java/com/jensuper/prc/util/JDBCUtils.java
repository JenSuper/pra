package com.jensuper.prc.util;

import com.jensuper.prc.word.ColumnInfo;
import com.jensuper.prc.word.TableInfo;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * JDBC 工具类
 */
public class JDBCUtils {
    private static String url;
    private static String user;
    private static String password;
    private  static String driver;

    /**
     * 文件读取，只会执行一次，使用静态代码块
     */
    static {
        //读取文件，获取值
        try {
            //1.创建Properties集合类
            Properties pro = new Properties();
            //获取src路径下的文件--->ClassLoader类加载器
            ClassLoader classLoader = JDBCUtils.class.getClassLoader();
            URL resource = classLoader.getResource("jdbc.properties");
            String path = resource.getPath();
            //2.加载文件
            pro.load(new FileReader(path));
            //3获取数据
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");
            //4.注册驱动
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取连接
     * @return 连接对象
     */
    public static Connection getConnection(){
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
        props.setProperty("remarks", "true"); //设置可以获取remarks信息
        props.setProperty("useInformationSchema", "true");//设置可以获取tables remarks信息
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,props);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 释放资源
     * @param rs
     * @param st
     * @param conn
     */
    public static void close(ResultSet rs, Statement st,Connection conn){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(st != null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取表及表名
     * @param conn
     */
    public static List<TableInfo> getTables(Connection conn){
        List<TableInfo> tableList = new ArrayList<>();
        try {
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet tables = metaData.getTables(null, "%", "%", new String[]{"TABLE"});

            while (tables.next()) {

                String remark = tables.getString("REMARKS");
                String tableName = tables.getString("TABLE_NAME");
                ResultSet columns = metaData.getColumns(null, "%", tableName, "%");

                TableInfo tableBuild = TableInfo.builder().tableName(tableName).remark(remark).build();
                List<ColumnInfo> columnInfoList = new ArrayList<>();

                while (columns.next()) {
                    String columnName = columns.getString("COLUMN_NAME");
                    String typeName = columns.getString("TYPE_NAME");
                    String columnSize = columns.getString("COLUMN_SIZE");
                    if (columnName.contains("INT")) {
                        columnSize = Integer.valueOf(columnSize) + 1 + "";
                    }
                    String remarks = columns.getString("REMARKS");

                    ColumnInfo build = ColumnInfo.builder().columnName(columnName).columnType(typeName).columnSize(columnSize).remark(remarks).build();
                    columnInfoList.add(build);
                }
                tableBuild.setColumnInfo(columnInfoList);
                tableList.add(tableBuild);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableList;
    }

    public static void main(String[] args) {
        Connection connection = getConnection();
        List<TableInfo> tables = getTables(connection);

        StringBuilder sr = new StringBuilder();
        AtomicInteger atomicInteger = new AtomicInteger();
        tables.forEach(table -> {
            atomicInteger.getAndIncrement();
            StringBuilder srCol = new StringBuilder();
            String remark = table.getRemark();
            String tableName = table.getTableName();
            sr.append(remark+"表包含").append(table.getColumnInfo().size()).append("个字段,id为主键，作为数据唯一标识");
            table.getColumnInfo().forEach(column -> {
                String columnName = column.getColumnName();
                String columnSize = column.getColumnSize();
                String columnType = column.getColumnType();
                String remark1 = column.getRemark();
                sr.append(columnName).append("用来存储").append(remark1).append(",");
                srCol.append(columnName).append("\t").append(columnType).append("\t").append(columnSize).append("\t").append(remark1).append("\n");
            });
            sr.append("表的设计如表4." + atomicInteger.get() + "所示").append("\n\n");
            sr.append("表4." + atomicInteger.get() + "  ").append(remark).append("表（" + tableName + "）").append("\n\n");
            sr.append(srCol);
            sr.append("\n").append("=================================================================").append("\n\n");
        });

        try {
            File file = new File("F:\\java\\myself\\pra\\src\\main\\resources\\templates\\out.txt");
            FileUtils.deleteQuietly(file);
            FileUtils.write(file, sr, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

