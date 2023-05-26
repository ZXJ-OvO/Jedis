package zxj.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import zxj.jedis.util.JedisConnectionFactory;

import java.util.Map;

/**
 * Junit5 常用注解 https://blog.csdn.net/winterking3/article/details/123870525
 */
public class JedisTest {
    private Jedis jedis;

    @BeforeEach
    void setUp() {
        // 1.建立连接
        // jedis = new Jedis("localhost", 6379);
        jedis = JedisConnectionFactory.getJedis();

        // 2.设置密码
        // jedis.auth("root");

        // 3. 选择库
        jedis.select(0);
    }

    /**
     * String类型数据
     */
    @Test
    void testString() {
        // 存入数据
        String result = jedis.set("name", "周欣杰");
        System.out.println("result = " + result);

        // 获取数据
        String name = jedis.get("name");
        System.out.println("name = " + name);
    }

    /**
     * 哈希类型数据
     */
    @Test
    void testHash(){
        // 插入hash数据
        jedis.hset("user:1","name","Jack");
        jedis.hset("user:1","age","21");

        // 获取hash数据
        Map<String, String> map = jedis.hgetAll("user:1");
        System.out.println(map);
    }

    @AfterEach
    void tearDown() {
        if (jedis != null) {
            // 关闭连接：连接对象没有被销毁，而是被归还到连接池中去了
            jedis.close();
        }
    }
}
