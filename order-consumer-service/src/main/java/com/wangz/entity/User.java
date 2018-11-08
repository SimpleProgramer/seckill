package com.wangz.entity;

import com.stip.mybatis.generator.plugin.BaseModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class User extends BaseModel<Long> implements Serializable {
    private Long id;

    private String userName;

    private String password;

    private String phone;

    private static final long serialVersionUID = 1L;

}