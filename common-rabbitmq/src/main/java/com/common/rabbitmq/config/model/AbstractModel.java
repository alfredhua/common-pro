package com.common.rabbitmq.config.model;

import com.common.rabbitmq.consumer.AbstractMqConsumer;
import com.rabbitmq.client.Channel;

public abstract class AbstractModel {

    public abstract void startModel(Channel channel ,AbstractMqConsumer abstractMqConsumer)throws Exception ;

}
