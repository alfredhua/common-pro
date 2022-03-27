package com.common.demo;

import com.common.rabbitmq.RabbitMqCore;
import com.common.redis.RedisCore;
import com.common.zk.ZkCore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ RedisCore.class, ZkCore.class, RabbitMqCore.class})
public class StartApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

}
