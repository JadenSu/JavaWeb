package cn.itcast.jdbcTemplate;

import cn.itcast.utils.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * JdebcTempate的基本使用  updata(); DML语句 增删改
 */
public class JdbcTemplateDemo1 {
    public static void main(String[] args) {
        //导入jar包
        //创建JDBCTemplate对象
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        //调用方法
        String sql = "update account set balance = 666 where id = ?";
        int count = template.update(sql, 2);
        System.out.println(count); //1  和 executeUpdate 类似
    }
}
