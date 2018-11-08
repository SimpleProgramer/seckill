package com.wangz.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 *
 * @author qijiang @Email jiang1211@foxmail.com
 * @date 2018/10/17 上午10:57
 * @Description 通过广播方式绑定队列创建和消息绑定
 */
@Configuration
public class RabbitFanoutConfig {

    public  final static String queue = "orderQueue";
    public  final static String exchange = "orderExchange";

    //注册消息中心queue队列
    @Bean
    public static Queue createTransferQueue(){
        return new Queue(queue);
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(exchange);
    }

    @Bean
    Binding bindingExchangeA(Queue createTransferQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(createTransferQueue).to(fanoutExchange);
    }

}
