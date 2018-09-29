package cn.itcast.jdbcTemplate;


import cn.itcast.domain.Emp;
import cn.itcast.utils.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * JdebcTempate的基本使用
 * 练习：
 * 需求：
 1. 修改1号数据的 salary 为 10000
 2. 添加一条记录
 3. 删除刚才添加的记录
 4. 查询id为1的记录，将其封装为Map集合
 5. 查询所有记录，将其封装为List
 6. 查询所有记录，将其封装为Emp对象的List集合
 7. 查询总记录数
 */
public class JdbcTemplateDemo2 {
    //Junit 单元测试方，方法独立运行
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Test
    public void test1(){
        String sql = "update emp set salary = 10000 where id = 1001";
        int count = template.update(sql);
    }


    @Test
    public void test2(){
        String sql = "insert into emp (id,ename,dept_id)values(?,?,?)";
        int count = template.update(sql, 1015, "打飞机", 10);
        System.out.println(count);
    }

    @Test
    public void test3(){
        String sql = "delete from emp where id = ?";
        int count = template.update(sql, 1015);
        System.out.println(count);
    }

    // 4. 查询id为1001的记录，将其封装为Map集合
    @Test
    public void test4(){
        String sql = "select *from emp where id = ?";
        Map<String, Object> map = template.queryForMap(sql,1001);
        System.out.println(map);
    }

    //5. 查询所有记录，将其封装为List
    @Test
    public void test5(){
        String sql = "select *from emp";
        List<Map<String, Object>> list = template.queryForList(sql);
        System.out.println(list);
    }

    //6. 查询所有记录，将其封装为Emp对象的List集合
    @Test
    public void test6(){
        String sql = "select *from emp";
        List<Emp> list = template.query(sql, new BeanPropertyRowMapper<Emp>(Emp.class));
        for (Emp emp : list) {
            System.out.println(emp);
        }
    }

    //7. 查询总记录数   聚合函数查询 queryForObject()
    @Test
    public void test07(){
         String sql = "select count(id) from emp";
        Long total = template.queryForObject(sql, Long.class);
        System.out.println(total);
    }
}
