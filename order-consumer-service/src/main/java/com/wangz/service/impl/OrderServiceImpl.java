package com.wangz.service.impl;

import com.wangz.models.req.OrderRequest;
import com.wangz.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @author wangzun
 * @Desc 订单消费业务
 * @version 2018/11/7 上午10:58
 **/
@Service
public class OrderServiceImpl implements OrderService {


	@Override
	public void consumerOrder(OrderRequest orderRequest) {
		//重复消费处理，根据userId，商品id，秒杀id验证是否已下单

	}
}
