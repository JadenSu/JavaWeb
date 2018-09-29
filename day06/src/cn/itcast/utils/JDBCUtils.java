package cn.itcast.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 创建一个数据连接池工具类
 * 功能封装
 */
public class JDBCUtils {
    //定义成员变量 DataSource
    private static DataSource ds;
    //静态代码块配置文件,获取连接池对象 ds
    static {
        try {
            //加载配置文件
            Properties pro = new Properties();
            ClassLoader classLoader = JDBCUtils.class.getClassLoader();
            InputStream is = classLoader.getResourceAsStream("druid.properties");
            pro.load(is);
            //ds
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //获取连接对象法法 ds.getConnection
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    //释放资源方法 方法重载(statement stmt ,Connection conn)    (ResultSet rs,PreparedStatement ps,Connection conn)
    public static void close(Statement stmt , Connection conn){
        //非空判断
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs, PreparedStatement ps, Connection conn){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //获取连接池对象
    public static DataSource getDataSource(){
        return ds;
    }
}
