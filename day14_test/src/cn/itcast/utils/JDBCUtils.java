package cn.itcast.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * JDBC 工具类 , 使用Druid连接池
 *
 * 复习:
 * 成员变量 ds 静态代码块加载配置文件  处置话连接池对象
 * 获取连接池对象
 * 获取连接对象
 */
public class JDBCUtils {
    //成员变量ds
    private static DataSource ds;
    //静态代码块
    static {
        //加载配置文件   使用Properties  获取流  类加载器加载配置文件
        try {
            Properties pro = new Properties();
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(is);

            //初始化连接对象  工厂方法
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取连接池对象方法
    public static DataSource getDataSource(){
        return ds;
    }

    //获取连接Connection对象
    public static Connection getConnction() throws SQLException {
        return ds.getConnection();
    }

    //释放资源方法

}
