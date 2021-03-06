package com.wangz.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import com.wangz.dao.OrderDao;
import com.wangz.entity.Order;
import com.wangz.entity.example.OrderExample;
import com.wangz.enums.ErrorCode;
import com.wangz.exceptions.BusinessException;
import com.wangz.models.req.OrderRequest;
import com.wangz.models.resp.ApiResponse;
import com.wangz.service.OrderService;
import com.wangz.service.ProductDubboService;
import com.wangz.utils.SnowFlakeIdWorker;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author wangzun
 * @Desc 订单消费业务
 * @version 2018/11/7 上午10:58
 **/
@Service
public class OrderServiceImpl implements OrderService {


	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private OrderDao orderDao;

	@Reference(version = "1.0.0")
	private ProductDubboService productDubboService;

	@Override
	@Transactional(rollbackFor = {Exception.class,RuntimeException.class})
	public void consumerOrder(OrderRequest orderRequest) {
		//重复消费处理，根据userId，商品id，秒杀id验证是否已下单
		OrderExample example = new OrderExample();
		example.createCriteria().andUserIdEqualTo(orderRequest.getUserId())
								.andProductIdEqualTo(orderRequest.getProductId())
								.andSeckillIdEqualTo(orderRequest.getSecKillId());
		List<Order> orders = orderDao.selectByExample(example);
		if(CollectionUtil.isNotEmpty(orders)) {
//			throw new BusinessException(ErrorCode.ORDER_REPEAT);
			return;
		}
		//扣减库存
		ApiResponse<Integer> productResp = productDubboService.deductInventory(orderRequest.getProductId());
		if(!ErrorCode.SUCCESS.getCode().equals(productResp.getCode())) {
//			throw new BusinessException(productResp.getCode(), productResp.getMsg());
			return;
		}else {
			if (1 != productResp.getData()) {
//				throw new BusinessException(ErrorCode.STORE_NOT_ENOUGH);
				return;
			}
		}
		Order order = new Order();
		order.setOrderId(SnowFlakeIdWorker.longUniqueSequenceId());
		order.setUserId(orderRequest.getUserId());
		order.setProductId(orderRequest.getProductId());
		order.setSeckillId(orderRequest.getSecKillId());
		order.setOrderStatus(1);
		order.setDeleteStatus(1);
		order.setCreateTime(new Date());
		order.setUpdateTime(order.getCreateTime());
		order.setTotalFee((int)orderRequest.getPrice().doubleValue() * 100);
		orderDao.insertSelective(order);
	}
}
