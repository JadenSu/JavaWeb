package cn.itcast.dao;

import cn.itcast.domain.User;

import java.util.List;

/**
 * 用户操作的DAO
 */
public interface UserDao {


    public List<User> findAll();

    User findUserByUsernameAndPassword(String username, String password);

    void add(User user);

    void delete(int id);

    User findById(int i);

    void update(User user);

    int findTotalCount();

    List<User> findByPage(int start, int rows);
}
