package cn.itcast.service.impl;

import cn.itcast.dao.ProvinceDao;
import cn.itcast.dao.impl.ProvinceDaoImpl;
import cn.itcast.domain.Province;
import cn.itcast.service.ProvinceService;
import cn.itcast.util.JedisPoolUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.util.List;

public class ProvinceServletImpl implements ProvinceService {

    //声明dao
    private ProvinceDao dao = new ProvinceDaoImpl();

    /**
     * 查询redis 未查询到数据则调用 findDB()查询数据库
     * @return
     */
    @Override
    public String findRedis() {
        //先查询redis 不为空就返回 为空则查询数据库储存到redis  findDB()
        //1.查询redis
        //1.1 创建Jedis连接
        Jedis jedis = JedisPoolUtils.getJedis();
        String json = jedis.get("province");
        //2.json进行判断
        if(json==null||json.length()==0){
            //redis未查询到数据
            System.out.println("redis未查询到数据------");
            //2.1查询mysql
            List<Province> list = dao.findDB();
            //2.2 将List序列化为json
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                json = objectMapper.writeValueAsString(list);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            //2.3 将list_json存入 redis
            jedis.set("province",json);
        }else{
            System.out.println("查询redis缓存-------");
        }
        return json;
    }

}
