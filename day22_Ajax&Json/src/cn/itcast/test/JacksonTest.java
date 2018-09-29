package cn.itcast.test;

import cn.itcast.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JacksonTest {


    @Test
    public void test1()throws Exception{
        //创建对象   将Java对象转化成Json对象
        User user1 = new User();
        user1.setUsername("zhangsan");
        user1.setPassword("123");
        //创建 ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        //两种常用方法 writeValue(参数1,Object)  writeValueAsString
        String user1str = objectMapper.writeValueAsString(user1);
        System.out.println(user1str);//{"username":"zhangsan","password":"123"}
    }
    @Test
    public  void test2() throws JsonProcessingException {
        //创建 list
        List<User> list = new ArrayList<User>();
        User user1 = new User();
        user1.setUsername("zhangsan");
        user1.setPassword("123");

        User user2 = new User();
        user2.setUsername("lisi");
        user2.setPassword("222");
        list.add(user1);
        list.add(user2);

        //创建 objectMapper  list  map 转换过程相同  格式不一样
        ObjectMapper objectMapper = new ObjectMapper();
        String liststr = objectMapper.writeValueAsString(list);
        System.out.println(liststr);
    }

    @Test
    public void test3() throws JsonProcessingException {
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("zhangsan","666");
        map1.put("lisi","777");
        ObjectMapper objectMapper = new ObjectMapper();
        String mapstr1 = objectMapper.writeValueAsString(map1);
        System.out.println(mapstr1);//{"lisi":"777","zhangsan":"666"}

        HashMap<String, User> map2 = new HashMap<>();
        User user1 = new User();
        user1.setUsername("zhangsan");
        user1.setPassword("123");

        User user2 = new User();
        user2.setUsername("lisi");
        user2.setPassword("222");

        map2.put("第一个",user1);
        map2.put("第二个",user2);
        String mapstr2 = objectMapper.writeValueAsString(map2);
        //{"第二个":{"username":"lisi","password":"222"},"第一个":{"username":"zhangsan","password":"123"}}


        System.out.println(mapstr2);


    }
}
