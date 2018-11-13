package com.wangz.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wangz.dao.ProductDao;
import com.wangz.entity.Product;
import com.wangz.entity.example.ProductExample;
import com.wangz.enums.ErrorCode;
import com.wangz.exceptions.BusinessException;
import com.wangz.models.resp.ApiResponse;
import com.wangz.service.ProductDubboService;
import com.wangz.utils.CheckParam;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

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
    public ApiResponse<Product> findProductById(Long productId) {
        ApiResponse<Product> resp = ApiResponse.ok();
        Product product = findProductById2DB(productId);

        if (CheckParam.isNull(product)) return null;

        if (product.getStore() < 1) {
            throw new BusinessException(ErrorCode.STORE_NOT_ENOUGH);
        }
        resp.setData(product);
        return resp;
    }

    @Override
    public ApiResponse<Integer> deductInventory(Long productId) {
        ApiResponse<Integer> resp = ApiResponse.ok();
        Product product = findProductById2DB(productId);

        if (CheckParam.isNull(product)) {
            throw new BusinessException(ErrorCode.NO_PRODUCT_CODE);
        }
        ProductExample example = new ProductExample();
        example.createCriteria().andStoreEqualTo(product.getStore())
                .andIdEqualTo(productId)
                .andIsDeleteEqualTo(true)
                .andUpdateTimeEqualTo(product.getUpdateTime());
        product.setStore(product.getStore() - 1);
        product.setUpdateTime(new Date());
        int resu = productDao.updateByExample(product,example);
        resp.setData(resu);
        return resp;
    }

    private Product findProductById2DB(Long productId) {
        return productDao.selectByPrimaryKey(productId);
    }

}
