package com.wangz.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wangz.entity.Product;
import com.wangz.entity.User;
import com.wangz.enums.ErrorCode;
import com.wangz.exceptions.BusinessException;
import com.wangz.models.req.OrderRequest;
import com.wangz.model.req.SecKillRequest;
import com.wangz.model.resp.SecKillResponse;
import com.wangz.models.resp.ApiResponse;
import com.wangz.service.ProductDubboService;
import com.wangz.service.SecKillService;
import com.wangz.service.UserDubboService;
import com.wangz.utils.CheckParam;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangzun
 * @version 2018/11/7 下午4:50
 * @desc
 */
@Service
public class SecKillServiceImpl implements SecKillService {

    @Reference(version = "1.0.0")
    private UserDubboService userDubboService;
    @Reference(version = "1.0.0")
    private ProductDubboService productDubboService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public SecKillResponse try2Kill(SecKillRequest request) {

        //1.先获取基本信息。
        //2.检测用户是否是异常状态
        //3.检测库存是否充足
        //4.发送下单消息到订单中心。
        User user = userDubboService.findUserByUserId(request.getUserId());
        if (CheckParam.isNull(user)) {
            throw new BusinessException(ErrorCode.NO_USER_CODE);
        }
        //略过用户状态监测，因为没设计。

        ApiResponse<Product> productResp = productDubboService.findProductById(request.getProductId());
        if (CheckParam.isNull(productResp)) {
            throw new BusinessException(ErrorCode.NO_PRODUCT_CODE);
        }
        if (!ErrorCode.SUCCESS.getCode().equals(productResp.getCode())) {
            throw new BusinessException(productResp.getCode(), productResp.getMsg());
        }
        //到这里说明成功 通知订单中心处理数据,解决重复消费问题，由本服务获取一个订单号。
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setUserId(user.getId());
        orderRequest.setPrice(productResp.getData().getPrice());
        orderRequest.setProductId(productResp.getData().getId());
        orderRequest.setCreateTime(System.currentTimeMillis());
        orderRequest.setSecKillId(request.getSecKillId());
        rabbitTemplate.convertAndSend("orderExchange", "orderQueue", orderRequest);
        return new SecKillResponse("ok");
    }
}
