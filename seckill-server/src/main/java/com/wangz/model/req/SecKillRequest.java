package com.wangz.model.req;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author wangzun
 * @version 2018/11/7 下午4:20
 * @desc
 */
@Data
public class SecKillRequest {

    @NotNull
    @Min(1)
    @ApiModelProperty(value = "秒杀用户id",example = "1",required = true)
    private Long userId;

    @NotNull
    @Min(1)
    @ApiModelProperty(value = "秒杀产品id",example = "1",required = true)
    private Long productId;
}
