package cn.itcast.datasource.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class C3P0Demo1 {
    public static void main(String[] args) throws  Exception{

        DataSource ds = new ComboPooledDataSource();

        Connection conn = ds.getConnection();

        String sql = "select *from user WHERE  id = ?";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1,2);

        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            String name = rs.getString(2);

            System.out.println(name);
        }

        DataSource otherc3p0 = new ComboPooledDataSource("otherc3p0");

        Connection conn2 = otherc3p0.getConnection();

        PreparedStatement ps2 =conn2.prepareStatement(sql);

        ps2.setInt(1,2);

        ResultSet rs2 = ps2.executeQuery();

        while (rs2.next()){
            int password = rs.getInt(3);
            System.out.println(password);
        }

    }
}
