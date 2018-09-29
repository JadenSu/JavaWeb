package cn.itcast.dao.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        //通过账号密码查询数据库是否有该用户 1.sql 2.执行sql
        String sql = "select * from user where username = ? and password = ?";
        //查询数据 将其封装为user 对象
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
        return user;
    }

    @Override
    public void add(User user) {
        String sql = "insert into user values(null,?,?,?,?,?,?,null,null)";
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail());
    }

    @Override
    public void delete(int id) {
        String sql = "delete from user where id = ?";
        template.update(sql,id);
    }

    @Override
    public User findUserById(int id) {
        String sql = "select *from user where id = ?";
        //将查到的数据封装封装成user对象
        return template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),id);
    }

    @Override
    public void update(User user) {
        String sql = "update user set name=?,gender = ?, age = ? ,address = ? ,qq = ?, email = ? where id =?";
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail(),user.getId());
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        //添加查询条件
        String sql = "select count(*) from user where 1 = 1 ";
        //遍历条件 将条件sql添加
        StringBuilder sb = new StringBuilder(sql);
        Set<String> keySet = condition.keySet();
        //条件参数集合
        List<Object> params = new ArrayList<>();
        for (String key : keySet) {
            //排除分页条件参数  ???
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }
            //获取 条件参数
            String value = condition.get(key)[0];//返回数组  取第一个
            //对参数进行判断
            if(value!=null&& !"".equals(value)){
                //条件有值
                sb.append(" and " +key+" like ? "); //sql添加
                params.add("%"+value+"%");
            }
        }

        sql = sb.toString();
        System.out.println("查数量语句"+sql);
        return template.queryForObject(sql,Integer.class,params.toArray()); //返回数值
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from user  where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        Set<String> keySet = condition.keySet();
        //条件参数集合
        List<Object> params = new ArrayList<>();
        for (String key : keySet) {
            //排除分页条件参数  ???
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }
            //获取 条件参数
            String value = condition.get(key)[0];//返回数组  取第一个
            //对参数进行判断
            if(value!=null&& !"".equals(value)){
                //条件有值
                sb.append(" and "+key+" like ?  "); //sql添加
                params.add("%"+value+"%");
            }
        }
        sb.append("limit ?,?");
        params.add(start);
        params.add(rows);
        sql = sb.toString();
        System.out.println("查list语句"+sql);
        return template.query(sql,new BeanPropertyRowMapper<User>(User.class),params.toArray());
    }
}
