package cn.itcast.dao;

import cn.itcast.domain.User;
import cn.itcast.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 操作数据库中User表的类
 */
public class UserDao {
    //声明JDBCTemplate  共用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    //登录方法 login 检验用户名和密码  根据user返回一个user对象
    public User login(User loginUser){
        try {
            //查询语句sql
            String sql = "select * from user where username = ? and password = ?";
            //调用querForObject 方法返回一个对象
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), loginUser.getUsername(), loginUser.getPassword());
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null; //进行非空判断 ,而不是返回值为null时报错
        }
    }


}
