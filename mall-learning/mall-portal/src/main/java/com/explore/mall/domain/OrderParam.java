package com.explore.mall.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 生成订单时传入的参数
 * Created by xinrongliao on 2021/1/20
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class OrderParam {
    @ApiModelProperty("收货地址")
    private Long memberReceiveAddressId;
    @ApiModelProperty("优惠卷ID")
    private Long couponId;
    @ApiModelProperty("使用的积分数")
    private Integer useIntegeration;
    @ApiModelProperty("支付方式")
    private Integer payType;
    @ApiModelProperty("被选中的购物车商品ID")
    private List<Long> cartIds;
}
