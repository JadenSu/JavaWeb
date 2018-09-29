package cn.itcast.jdbc;

import java.sql.*;

public class JDBCDemo3 {
    public static void main(String[] args)throws Exception {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取数据库连接对象
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db3", "root", "root");
            //定义sql语言语句
//            String sql = "insert into account values(null,'王五',3000)"; //DML 语句
//            String sql = "create table student (id int,name varchar(30))"; //  DDL 返回值为0
            String sql = "SELECT *FROM account";//DCL语句  使用 statement.executeQuery
            //获取执行sql的对象 Statement
            statement = connection.createStatement();
            //执行sql
            rs = statement.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt(1);//ID
                String name = rs.getString(2);//姓名
                double balance = rs.getDouble(3);//balance
                System.out.println(id+"---"+name+"---"+balance);
            }


//            System.out.println(count); // 1  程序成功
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            //释放资源  需要进行非空判断
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
}
