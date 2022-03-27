package com.common.demo.rabbitmq;

import com.common.rabbitmq.consumer.AbstractMqConsumer;
import com.common.util.GsonUtil;
import org.springframework.stereotype.Component;

@Component
public class DemoConsumer extends AbstractMqConsumer {

    @Override
    public String getTopic() {
        return "demo";
    }

    @Override
    public String consume(Object o) {
        System.out.println(GsonUtil.toJSON(o));
        return "";
    }

}
