package com.demo.mybtatis;

import com.common.demo.dao.DemoMapper;
import com.common.demo.rabbitmq.Demo;
import com.common.util.IDGenerateUtil;
import com.demo.TestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class MybatisTest extends TestBase {

    @Autowired
    DemoMapper demoMapper;

    @Test
    public void  insert() {
        Demo demo = new Demo();
        demo.setId(IDGenerateUtil.generateId());
        demo.setName("aaaa");
//        demo.setUpdateTime(LocalDateTime.now());
//        demo.setCreateTime(LocalDateTime.now());
        demoMapper.insert(demo);
    }

    @Test
    public void  update() {
        Demo demo = new Demo();
        demo.setId(1473039862595616L);
        demo.setName("aaaa");
//        demo.setUpdateTime(LocalDateTime.now());
//        demo.setCreateTime(LocalDateTime.now());
        demo.setModifier("demo");
        boolean b = demoMapper.updateById(demo);
        System.out.println(b);
    }

    @Test
    public void  updateTwo() {
        Demo demo = new Demo();
        demo.setId(1473039862595616L);
//        demo.setUpdateTime(LocalDateTime.now());
        demo.setModifier("demo");
        boolean b = demoMapper.updateById(demo);
        System.out.println(b);
    }

    @Test
    public void  updateById() {
        Demo demo = new Demo();
//        demo.setUpdateTime(LocalDateTime.now());
        demo.setModifier("dessssmo");
        boolean b = demoMapper.updateById(demo);
        System.out.println(b);
    }

    @Test
    public void  deleteById() {
        boolean b = demoMapper.deleteById(1473039862595616L);
        System.out.println(b);
    }

    @Test
    public void query(){
        Demo demo = demoMapper.queryById(1473133481558048L);
        System.out.println(demo.toString());
    }
}
