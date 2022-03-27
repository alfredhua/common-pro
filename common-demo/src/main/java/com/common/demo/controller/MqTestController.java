package com.common.demo.controller;

import com.common.demo.rabbitmq.Demo;
import com.common.rabbitmq.client.MqClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
public class MqTestController {

    @RequestMapping("/demo")
    public void send(String a) throws IOException, TimeoutException {
        Demo demo = new Demo();
        demo.setId(a);
        MqClient.send("demo",demo);
    }
}
