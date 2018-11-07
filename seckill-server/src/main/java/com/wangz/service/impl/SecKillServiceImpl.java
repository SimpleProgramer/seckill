package com.wangz.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wangz.entity.Product;
import com.wangz.entity.User;
import com.wangz.enums.ErrorCode;
import com.wangz.exceptions.BusinessException;
import com.wangz.model.req.SecKillRequest;
import com.wangz.model.resp.SecKillResponse;
import com.wangz.service.ProductDubboService;
import com.wangz.service.SecKillService;
import com.wangz.service.UserDubboService;
import com.wangz.utils.CheckParam;
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
        Product product = productDubboService.findProductById(request.getProductId());
        if (CheckParam.isNull(product)) {
            throw new BusinessException(ErrorCode.NO_PRODUCT_CODE);
        }
        //简单的反作弊维度 ：账号，ip，购买物品，


        return null;
    }
}
