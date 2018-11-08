package com.wangz.models.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author wangzun
 * @version 2018/11/8 下午2:34
 * @desc
 */
@Data
public class OrderRequest implements Serializable {
    private static final long serialVersionUID = 507159935208271218L;
    @NotNull
    @Min(1)
    @ApiModelProperty(value = "秒杀用户id",example = "1",required = true)
    private Long userId;

    @NotNull
    @Min(1)
    @ApiModelProperty(value = "秒杀产品id",example = "1",required = true)
    private Long productId;

    @NotNull
    @Min(1)
    @ApiModelProperty(value = "商品单价id",example = "1",required = true)
    private BigDecimal price;

    @NotNull
    @Min(1)
    @ApiModelProperty(value = "创建时间",example = "1503453253452",required = true)
    private Long createTime;//timestamp

    @NotNull
    @Min(1)
    @ApiModelProperty(value = "秒杀id",example = "1",required = true)
    private Long secKillId;

}
