package com.wangz.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wangz.dao.ProductDao;
import com.wangz.dao.UserDao;
import com.wangz.entity.Product;
import com.wangz.entity.User;
import com.wangz.enums.ErrorCode;
import com.wangz.exceptions.BusinessException;
import com.wangz.service.ProductDubboService;
import com.wangz.utils.CheckParam;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * @author wangzun
 * @version 2018/11/7 上午10:58
 * @Desc user生产服务
 **/
@Service(version = "1.0.0")
public class ProductDubboServiceImpl implements ProductDubboService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product findProductById(Long productId) {
        Product product = findProductById2DB(productId);
        if(CheckParam.isNull(product)) return null;
        if(product.getStore() < 1) {
            throw new BusinessException(ErrorCode.STORE_NOT_ENOUGH);
        }

        return null;
    }

    private Product findProductById2DB(Long productId) {
        return productDao.selectByPrimaryKey(productId);
    }

}
