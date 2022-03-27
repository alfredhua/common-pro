package com.demo.redis;

import com.common.redis.client.RedisClient;
import com.demo.TestBase;
import org.junit.Test;

public class RedisTest extends TestBase {

    @Test
    public void objectSet(){
        RedisClient.objectSet("1",1L,"111");
    }
}
