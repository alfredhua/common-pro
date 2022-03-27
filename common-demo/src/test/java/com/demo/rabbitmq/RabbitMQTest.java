package com.demo.rabbitmq;

import com.common.rabbitmq.client.MqClient;
import com.demo.TestBase;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQTest extends TestBase {

    @Test
    public void test(){
        try {
            MqClient.send("demo","aaaaaaaaaa");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
