package cn.itcast.jdbcTemplate;

import cn.itcast.domain.Emp;
import cn.itcast.utils.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * 	4. 练习：
 * 需求：
 1. 修改1号数据的 salary 为 10000
 2. 添加一条记录
 3. 删除刚才添加的记录
 4. 查询id为1的记录，将其封装为Map集合
 5. 查询所有记录，将其封装为List
 6. 查询所有记录，将其封装为Emp对象的List集合
 7. 查询总记录数

 */
public class JdbcTemplateDemo3 {
    //Junit单元测试，方法独立运行
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    //1. 修改所有数据的 salary 为 66666
    @Test
    public void test1(){
        String sql = "update emp set salary = ?";
        int count = template.update(sql, 6666);
        System.out.println(count);
    }

    // 2. 添加一条记录
    @Test
    public void test2(){
        String sql = "insert into emp(id,ename,salary) values (?,?,?)";
        int count = template.update(sql, 1015, "梧州", 8888);
        System.out.println(count);
    }

    // 3. 删除刚才添加的记录
    @Test
    public void test3(){
        String sql = "delete from emp where id = ?";
        int count = template.update(sql, 1015);
        System.out.println(count);
    }

    // 4. 查询id为1的记录，将其封装为Map集合
    @Test
    public void test4(){
        String sql = "SELECT *from emp where id = ?";
        Map<String, Object> map = template.queryForMap(sql,1001);
        System.out.println(map);
    }

    // 5. 查询所有记录，将其封装为List
    @Test
    public void test5(){
        String sql = "select *from emp";
        List<Map<String, Object>> list = template.queryForList(sql);
        for (Map<String, Object> stringObjectMap : list) {
            System.out.println(stringObjectMap);
        }
    }

    //6. 查询所有记录，将其封装为Emp对象的List集合
    @Test
    public void test6(){
        String sql = "select *from emp";
        List<Emp> list = template.query(sql, new BeanPropertyRowMapper<Emp>(Emp.class));
        System.out.println(list);
    }

    //7. 查询总记录数
    @Test
    public void test7(){
        String sql = "select count(*) from emp";
        //聚合函数 queryForObject
        Long total = template.queryForObject(sql, Long.class);
        System.out.println(total);
    }


}
