package cn.itcast.jdbc;

import cn.itcast.domain.Emp;

import java.sql.*;
import java.util.Date;
import java.util.List;

//定义一个方法，查询emp表的数据将其封装为对象，然后装载集合，返回。
public class JDBCDemo2 {
    public static void main(String[] args)  {

        List<Emp> list = new JDBCDemo2().findAll();
        System.out.println(list);


    }
    public List<Emp> findAll(){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Emp> list = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/db3", "root", "root");
            String sql = "select *from emp";

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Emp emp = null;

            while(resultSet.next()){
                int id = resultSet.getInt(1);
                String ename = resultSet.getString(2);
                int job_id = resultSet.getInt(3);
                int mgr = resultSet.getInt(4);
                Date joindate = resultSet.getDate(5);
                double salary = resultSet.getDouble(6);
                double bonus = resultSet.getDouble(7);
                int dep_id = resultSet.getInt(8);

                list.add(new Emp(id,ename,job_id,mgr,joindate,salary,bonus,dep_id));

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            //释放资源  需要进行非空判断
            try {
                resultSet.close();
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
        return list;
    }
}
