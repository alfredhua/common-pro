package com.common.demo.dao;

import com.common.demo.rabbitmq.Demo;
import com.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DemoMapper extends BaseMapper<Demo> {
}