package cn.itcast.datasource.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DruidDemo1 {
    public static void main(String[] args) throws Exception {
        //加载配置文件
        Properties pro = new Properties();
        ClassLoader classLoader = DruidDemo1.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("druid.properties");
        pro.load(is);
        //创建连接池对象
        DataSource ds = DruidDataSourceFactory.createDataSource(pro);
        //获取连接对象
        Connection conn = ds.getConnection();
        //剩下步骤一样

    }
}
