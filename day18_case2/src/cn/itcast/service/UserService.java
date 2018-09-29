package cn.itcast.service;

import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的业务接口
 */
public interface UserService {

    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> findAll();

    /**
     * 登录方法
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 添加User
     * @param user
     */
    void addUser(User user);

    void deleteUser(String id);

    User findUserById(String id);

    void updateUser(User user);

    void delSelectedUser(String[] ids);

    /**
     * 分页查询
     * @param rows
     * @param currentPage
     * @param condition
     * @return
     */
    PageBean<User> findUserByPage(String rows, String currentPage, Map<String, String[]> condition);
}
