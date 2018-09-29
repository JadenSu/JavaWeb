package cn.itcast.jdbc;

import java.sql.*;

public class Demo1 {
    public static void main(String[] args)throws Exception {
        Connection connection = null;
        Statement statement = null;

        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取数据库连接对象
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db3", "root", "root");
            //定义sql语言语句
//            String sql = "insert into account values(null,'王五',3000)"; //DML 语句
            String sql = "create table student (id int,name varchar(30))"; //  DDL 返回值为0
            //获取执行sql的对象 Statement
            statement = connection.createStatement();
            //执行sql
            int count = statement.executeUpdate(sql);
            //处理结果
            System.out.println(count); // 1  程序成功
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //释放资源  需要进行非空判断
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
