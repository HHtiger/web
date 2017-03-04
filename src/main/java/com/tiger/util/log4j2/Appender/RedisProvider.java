package com.tiger.util.log4j2.Appender;

/**
 * Package: com.tiger.log
 * ClassName: RedisProvider
 * Author: he_hu@founder.com.cn
 * Description:
 * CreateDate: 2016-04-04
 * Version: 1.0
 */

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.nosql.appender.NoSqlProvider;
import org.apache.logging.log4j.status.StatusLogger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

@Plugin(name = "Redis", category = "Core", printObject = true)
public class RedisProvider implements NoSqlProvider<RedisConnection> {
    private static final Logger LOGGER = StatusLogger.getLogger();

    private final JedisPool pool;

    private RedisProvider (JedisPool pool) {
        this.pool = pool;
    }

    @PluginFactory
    public static RedisProvider createNoSqlProvider (
            @PluginAttribute("host") final String host,
            @PluginAttribute("port") int port,
            @PluginAttribute("timeout") int timeout,
            @PluginAttribute("password") final String password,
            @PluginAttribute("database") int database,
            @PluginAttribute("clientName") final String clientName
    ) {
        if (host != null && host.length() > 0) {
            try {
                if (port <= 0) port = 6379;
                if (timeout <= 0) timeout = Protocol.DEFAULT_TIMEOUT;
                if (database <= 0) database = Protocol.DEFAULT_DATABASE;

                JedisPool pool = null;

                pool = new JedisPool(new JedisPoolConfig(), host, port, timeout, password, database, clientName);

                try (Jedis jedis = pool.getResource()) { }

                return new RedisProvider(pool);
            } catch (Exception e) {
                LOGGER.error("与Redis服务器建立连接时发生错误[{}:{}]：{}",e.getMessage(), host, port);
                return null;
            }
        }
        LOGGER.error("Redis 服务器地址[host]不能为空");
        return null;
    }

    @Override
    public RedisConnection getConnection() {
        return new RedisConnection(pool);
    }

}
