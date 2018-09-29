package cn.itcast.dao.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findAll() {
        //使用JDBC操作数据库...
        //1.定义sql
        String sql = "select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));

        return users;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        try {
            String sql = "select * from user where username = ? and password = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void add(User user) {
        //和删除类似
        String sql = "insert INTO user values(null,?,?,?,?,?,?,null,null)";
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail());//将参数按照顺序填充进去
    }

    @Override
    public void delete(int id) {
        //定义 sql
        String sql = "delete  from user where id =?";
        template.update(sql,id);
    }

    @Override
    public User findById(int id) {
        //定义sql
        String sql = "select*from user where id = ?";
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
        return user;
    }

    @Override
    public void update(User user) {
        //定义sql
        String sql = "update user set name = ? ,gender = ? , age = ?, address = ?,qq = ?,email = ?  where id = ?";
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail(),user.getId());//和add一样将参数传递进去
    }

    /**
     * 查询总记录数
     * @return
     */
    @Override
    public int findTotalCount() {
        String sql = "select count(*) from user";

        return template.queryForObject(sql,Integer.class);
    }

    /**
     * 查询页面数据
     * @param start
     * @param rows
     * @return
     */
    @Override
    public List<User> findByPage(int start, int rows) {
        String sql = "select *from user limit ?,?";

        return template.query(sql,new BeanPropertyRowMapper<User>(User.class),start,rows);
    }
}
