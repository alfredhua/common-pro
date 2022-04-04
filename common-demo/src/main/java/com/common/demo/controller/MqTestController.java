package com.common.demo.controller;

import com.common.demo.dao.DemoMapper;
import com.common.demo.rabbitmq.Demo;
import com.common.rabbitmq.client.MqClient;
import com.common.util.IDGenerateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.IdGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeoutException;

@RestController
public class MqTestController {

    @RequestMapping("/demo")
    public void send(String a) throws IOException, TimeoutException {
        Demo demo = new Demo();
        demo.setId(Long.parseLong(a));
        MqClient.send("demo",demo);
    }

    @Autowired
    DemoMapper demoMapper;

    @RequestMapping("/insert")
    @ResponseBody
    public String  insert() {
        Demo demo = new Demo();
        demo.setId(IDGenerateUtil.generateId());
        demo.setName("aaaa");
        demo.setUpdateTime(LocalDateTime.now());
        demo.setCreateTime(LocalDateTime.now());
        demoMapper.insert(demo);
        return "success";
    }
}
