package cn.itcast.service.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.impl.UserDaoImpl;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import org.omg.PortableInterceptor.USER_EXCEPTION;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        //调用Dao完成查询
        return dao.findAll();
    }

    @Override
    public void updateUser(User user) {
        dao.update(user);
    }

    @Override
    public void delSelectedUser(String[] ids) {
        if(ids!=null && ids.length>0){
            for (String id : ids) {
                dao.delete(Integer.parseInt(id));
            }
        }

    }

    @Override
    public PageBean<User> findUserByPage(String _rows, String _currentPage) {
        //1.参数类型转换
        int rows = Integer.parseInt(_rows);
        int currentPage = Integer.parseInt(_currentPage);
        //2.调用dao 获取 totalCount  list
        int totalCount = dao.findTotalCount();//获取总记录数不需要参数  select *from user
        int start = (currentPage-1)*rows;
        List<User> list = dao.findByPage(start,rows);//获取页面内容需要  索引start  和 rows  select* from user limit ?,?
        //3.计算 totalPage
        int totalPage = (totalCount%rows == 0 ? totalCount/rows:totalCount/rows +1);
        //4.创建 pb 进行参数设置 返回
        PageBean<User> pb = new PageBean<>();
        pb.setTotalPage(totalPage);
        pb.setList(list);
        pb.setTotalCount(totalCount);
        pb.setRows(rows);
        pb.setCurrentPage(currentPage);
        return pb;
    }

    public User login(User user){
        return dao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    public void deleteUser(String id) {
        //将id 转化成 INT  删除不需要返回值
        dao.delete(Integer.parseInt(id));
    }

    public void addUser(User user) {
        dao.add(user);
    }

    public User findUserById(String id) {
        return dao.findById(Integer.parseInt(id));
    }
}
