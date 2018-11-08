package com.wangz.message;

import com.wangz.models.req.OrderRequest;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wangzun
 * @version 2018/11/8 下午5:20
 * @desc
 */
@RabbitListener(queues = "orderQueue")
@Component
public class MessageReceiver {



    @RabbitHandler
    public Object process(OrderRequest orderRequest) {
        return null;
    }
}
