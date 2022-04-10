package com.common.demo;

import com.common.es.EsCore;
import com.common.mybatis.MybatisCore;
import com.common.rabbitmq.RabbitMqCore;
import com.common.redis.RedisCore;
import com.common.zk.ZkCore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ MybatisCore.class,RabbitMqCore.class,RedisCore.class,ZkCore.class, EsCore.class})
public class StartApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

}
