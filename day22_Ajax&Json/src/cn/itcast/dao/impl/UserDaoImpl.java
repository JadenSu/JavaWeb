package cn.itcast.dao.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;

public class UserDaoImpl implements UserDao {
    @Override
    public User findUser(String username) {
        //根据用户名进行判断 简单测试 不从数据库获取数据


        if(username == "shabi"){
            return null;
        }else {
            User user = new User();
            user.setPassword("666");
            user.setUsername("zhangsan");

            return user;
        }

    }
}
