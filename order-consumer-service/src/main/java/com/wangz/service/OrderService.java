package com.wangz.service;

import com.wangz.entity.User;
import com.wangz.models.req.OrderRequest;

public interface OrderService {

	//消费秒杀订单
	void consumerOrder(OrderRequest orderRequest);
}
