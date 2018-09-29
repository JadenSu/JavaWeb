package cn.itcast.jdbc;

import cn.itcast.jdbc.JDBCDemo9;
import cn.itcast.utils.JDBCUtils;

import java.sql.*;
import java.util.Scanner;

//账号密码登录案例   数据库 db3 user
public class JDBCDemo4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入账号：");
        String username = sc.nextLine();
        System.out.println("请输入密码：");
        String password = sc.nextLine();
        //调用方法进行判断
        boolean flag = new JDBCDemo9().login(username,password);
        if(flag){
            System.out.println("登陆成功");
        }else{
            System.out.println("登录失败");
        }

    }

    //登录方法
    public boolean login(String username,String password){
        if(username == null || password == null){
            return false;
        }
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            //连接数据库
            conn = JDBCUtils.getConnection();
            //定义 sql
            String sql = "select *from user where uername = ? and password = ? ";
            //获取sql执行对象  使用prepareStatmnt(); 该方法需要参数sql
            pstmt = conn.prepareStatement(sql);
            //sql赋值
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            //执行查询 sql在创建执行对象是已经传递，不需要再进行传递
            rs = pstmt.executeQuery();
            //判断
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs,pstmt,conn);
        }
        return false;
    }
}
