package cn.itcast.test;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void testLogin(){
        User loginuser = new User();
        loginuser.setUsername("jack");
        loginuser.setPassword("666");

        UserDao dao = new UserDao();
        User user = dao.login(loginuser);
        System.out.println(user);
    }
}
