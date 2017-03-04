package com.tiger.util.log4j2.Appender;

/**
 * Package: com.tiger
 * ClassName: aaa
 * Author: he_hu@founder.com.cn
 * Description:
 * CreateDate: 2016-04-04
 * Version: 1.0
 */

import org.apache.logging.log4j.core.appender.AppenderLoggingException;
import org.apache.logging.log4j.nosql.appender.DefaultNoSqlObject;
import org.apache.logging.log4j.nosql.appender.NoSqlConnection;
import org.apache.logging.log4j.nosql.appender.NoSqlObject;
import org.codehaus.jackson.map.ObjectMapper;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;


public class RedisConnection implements NoSqlConnection<Map<String, Object>, DefaultNoSqlObject> {
    private static final ObjectMapper mapper = new ObjectMapper();

    private final JedisPool pool;
    private final Jedis jedis;
    private final AtomicBoolean closed = new AtomicBoolean(false);

    public RedisConnection (JedisPool pool) {
        this.jedis = pool.getResource();
        this.pool = pool;
    }

    @Override
    public DefaultNoSqlObject createObject() {
        return new DefaultNoSqlObject();
    }

    @Override
    public DefaultNoSqlObject[] createList(int length) {
        // TODO Auto-generated method stub
        return new DefaultNoSqlObject[length];
    }

    @Override
    public void insertObject(NoSqlObject<Map<String, Object>> object) {
        try {
            jedis.lpush("web_core_log", mapper.writeValueAsString(object.unwrap()));
        } catch (Exception e) {
            throw new AppenderLoggingException("往Redis中插入数据时发生错误: " + e.getMessage(), e);
        }
    }

    @Override
    public void close() {
        if (closed.compareAndSet(false, true)) {
            this.pool.close();
        }
    }

    @Override
    public boolean isClosed() {
        return closed.get();
    }

}