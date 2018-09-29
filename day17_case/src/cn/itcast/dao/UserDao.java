package cn.itcast.dao;

import cn.itcast.domain.User;
import cn.itcast.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDao  {
    //查询User表数据方法
    public List<User> getAllUsers() throws SQLException {
        QueryRunner runner  = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select*from user";
        List<User> user = runner.query(sql,new BeanListHandler<User>(User.class));
        return user;

    }


}
