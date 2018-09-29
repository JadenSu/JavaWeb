package cn.itcast.service.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.impl.UserDaoImpl;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;

public class UserServiceImpl implements UserService {
    //先创建dao对象
    UserDao dao = new UserDaoImpl();
    @Override
    public User findUser(String username) {
        //调用dao方法  返回User

        return dao.findUser(username);
    }
}
