package cn.itcast.service.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.impl.UserDaoImpl;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;

import java.util.List;import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        //调用Dao完成查询
        return dao.findAll();
    }

    @Override
    public User login(User user) {
        //dao需要通过user的username 和 password 去数据库查找数据 并返回
        return dao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public void addUser(User user) {
        dao.add(user);
    }

    @Override
    public void deleteUser(String id) {
        dao.delete(Integer.parseInt(id));
    }

    @Override
    public User findUserById(String id) {

        return dao.findUserById(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        dao.update(user);
    }

    @Override
    public void delSelectedUser(String[] ids) {
        //遍历数组  进行删除
        for (String id : ids) {
            dao.delete(Integer.parseInt(id));
        }
    }

    @Override
    public PageBean<User> findUserByPage(String _rows, String _currentPage, Map<String, String[]> condition) {
        //调用dao  返回PageBean
        //创建PageBean
        PageBean<User> pb = new PageBean<>();
        //rows  currentPage  参数转换
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        //首页无法再往前翻
        if(currentPage<=0){
            currentPage=1;
        }
        //设置页面属性
        pb.setRows(rows);
        pb.setCurrentPage(currentPage);
        //调用dao 获取totalCount  再通过 totalCount 和 rows 计算 totalpage
        int totalCount = dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);
        //获取每页数据List集合  传入 第一个数据的索引  和 每页数
        //计算页面第一个记录索引   页面数-1 * 每页数
        int start = (currentPage -1)*rows;
        List<User> list = dao.findByPage(start,rows,condition); //返回 List 数组
        pb.setList(list);
        //获取总页码   totalCount/rows 向上去
        int totalPage =( totalCount % rows == 0 ? totalCount/rows:totalCount/rows+1);
        pb.setTotalPage(totalPage);
        //pb 所有参数设置完 rows  currentPage totalCount  totalPage  List  返回pb
        return  pb;
    }




}
