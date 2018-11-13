package com.wangz.entity;

import com.stip.mybatis.generator.plugin.BaseModel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Product extends BaseModel<Long> implements Serializable {
    private Long id;

    private String productName;

    private Long categoryId;

    private BigDecimal price;

    private Integer store;

    private String desc;

    private String imageUrl;

    private Date createTime;

    private Date updateTime;

    private Boolean isDelete;

    private Date deleteTime;

    private static final long serialVersionUID = 1L;

}