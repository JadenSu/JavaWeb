package cn.itcast.datasource.druid;

import cn.itcast.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DruidDemo2 {
    public static void main(String[] args) {
        Connection conn =null;
        PreparedStatement ps =null;
        ResultSet rs =null;
        try {
            //获取连接
            conn = JDBCUtils.getConnection();
            //定义sql
            String sql = "select *from account where id = ?";
            //获取执行对象
            ps = conn.prepareStatement(sql);
            //？赋值
            ps.setInt(1,1);
            //结果集
            rs = ps.executeQuery();
            //判读
            while (rs.next()){
                int balance = rs.getInt(3);
                System.out.println(balance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,ps,conn);
        }
    }
}
