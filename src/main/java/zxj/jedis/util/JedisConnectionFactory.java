package zxj.jedis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisConnectionFactory {
    private static final JedisPool jedisPool;

    static {
        // 配置连接池
        JedisPoolConfig poolConfig = new JedisPoolConfig();

        // 创建最大的连接数：最多只允许同时存在多少个连接对象
        poolConfig.setMaxTotal(8);

        // 创建最大的空闲连接数，没有访问也可以存在的连接对象
        poolConfig.setMaxIdle(8);

        // 创建最小连接数：连接对象长时间没有被访问则进行释放
        poolConfig.setMinIdle(0);

        // 创建最大等待时间：连接池中没有可用连接时，最大等待时间，默认值-1表示始终等待直到有可用连接为止
        poolConfig.setMaxWaitMillis(1000);

        // 创建连接池配置对象
        jedisPool = new JedisPool(poolConfig, "localhost", 6379, 1000);
    }

    /**
     * 获取一个Jedis连接池对象
     * @return 连接池对象
     */
    public static Jedis getJedis() {
        return jedisPool.getResource();
    }
}
