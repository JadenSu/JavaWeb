package cn.itcast.dao;


import cn.itcast.domain.User;
import cn.itcast.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * dao类 用于操作数据库
 *
 * 操作数据库中user表的类
 */
public class UserDao {
    //声明 Template对象    该案例只有一个登录方法,实际开发中会有多个方法需要调用 template,因此对其进行声明
    // 让该类中所有方法共用 template
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 登录方法
     * @param loginUser
     * @return user  登录方法需要 姓名和密码,返回的是一个完整的用户信息,目前该案例数据库只有 ID name 和 password
     *                在实际开发中数据中会有更多的信息
     */
    public User login(User loginUser){
        try {
            //sql语句
            String sql = "select * from user where username = ? and password = ?";
            //调用query方法   queryForOBject 封装成对象
            //  查询sql语句 将其封装成对象, 当sql查询不到对象会报错,我们希望不报错而是返回null
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class),
                    loginUser.getUsername(), loginUser.getPassword());
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;//不进行报错 而是返回null;
        }
    }
}
