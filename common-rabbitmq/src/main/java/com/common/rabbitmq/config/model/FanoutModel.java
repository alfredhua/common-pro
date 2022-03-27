package com.common.rabbitmq.config.model;

import com.common.rabbitmq.config.MqSupport;
import com.common.rabbitmq.constants.ModelEnum;
import com.common.rabbitmq.consumer.AbstractMqConsumer;
import com.rabbitmq.client.Channel;

public class FanoutModel extends AbstractModel {

    @Override
    public void startModel(Channel channel, AbstractMqConsumer abstractMqConsumer)throws Exception {
        String topic = abstractMqConsumer.getTopic();
        String exchangeName = MqSupport.initExchange(topic);
        channel.exchangeDeclare(exchangeName, ModelEnum.FANOUT.getModel());
        String queueName = MqSupport.initQueue(topic);
        channel.queueBind(queueName, exchangeName, "");
        channel.basicConsume(queueName, abstractMqConsumer.autoAck(), (consumerTag, delivery) -> {
            //处理接收MQ
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(topic+" Received :" + message);
            abstractMqConsumer.consume(message);
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }, consumerTag -> { });
    }
}
