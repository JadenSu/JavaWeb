package cn.itcast.jdbc;

import cn.itcast.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCDemo8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户");
        String username = sc.nextLine();
        System.out.println("请输入密码");
        String password = sc.nextLine();
        //调用方法
        boolean login = new JDBCDemo8().login(username, password);
        if(login){
            System.out.println("AMDYES");
        }else {
            System.out.println("AMDNONONO");
        }
    }

    public boolean login(String username, String password) {
        //非空判断
        if (username == null || password == null) {
            return false;
        }
        //连接 执行对象 结果集
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "Select *from user where username = ? and password = ? ";
            ps = conn.prepareStatement(sql);
            //sql赋值
            ps.setString(1,username);
            ps.setString(2,password);
            rs = ps.executeQuery();
            //进行判断
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, ps, conn);
        }

        return false;
    }
}

