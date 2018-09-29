package cn.itcast.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * JDBC工具类
 *
 * 成员变量 ds 静态代码块加载配置文件  初始化连接池对象
 * 获取连接池对象
 * 获取连接对象
 */
public class JDBCUtils {
    //数据连接池成员变量
    private static DataSource ds;


    //静态代码块加载文件   初始化连接池对象
    static{
        try {
            //加载配置文件
            Properties pro = new Properties();
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(is);

            //初始化连接对象
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取连接池对象
    public static DataSource getDataSource(){
        return  ds;
    }
    //获取连接对象

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

}
