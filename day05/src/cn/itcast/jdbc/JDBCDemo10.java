package cn.itcast.jdbc;

import cn.itcast.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//事物
public class JDBCDemo10 {
    public static void main(String[] args) {
        Connection conn = null;
                PreparedStatement ps1 =null;
                PreparedStatement ps2 =null;
        try {
            //获取连接 连接对象管理实务
            conn = JDBCUtils.getConnection();
            //开启事务 参数false开启
            conn.setAutoCommit(false);
            //定义sql
            String sql1 = "update account set balance -? where id = ?";
            String sql2 = "update account set balance +? where id = ?";
            //获取执行对象
            ps1 = conn.prepareStatement(sql1);
            ps2 = conn.prepareStatement(sql2);
            //设置参数
            ps1.setInt(1,66);
            ps1.setInt(2,1);
            ps2.setInt(1,66);
            ps2.setInt(2,2);
            //执行sql
            ps1.executeUpdate();
            ps2.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(ps1,conn);
            JDBCUtils.close(ps2,null);
        }
    }
}
