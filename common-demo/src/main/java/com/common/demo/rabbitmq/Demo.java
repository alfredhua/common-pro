package com.common.demo.rabbitmq;

import com.common.mybatis.annotation.Table;
import com.common.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Table("demo")
public class Demo extends BaseEntity implements Serializable {


    String name;


}
