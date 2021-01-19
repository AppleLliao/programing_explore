package com.explore.mall.dao;

import com.explore.mall.domain.OmsOrderDetail;
import com.explore.mall.model.OmsOrderItem;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 前台订单自定义Dao
 * Created by xinrongliao  on 2021/1/19
 */
public interface PortalOrderDao {

    /**
     * 获取订单及下单商品详情
     */
    OmsOrderDetail getDetail(@Param("orderId") Long orderId);
    /**
     * 获取超时订单
     * @param minute 超时时间（分）
     */
    List<OmsOrderDetail> getTimeOutOrders(@Param("minute")Integer minute);

    /**
     * 批量修改订单状态
     */
    int updateOrderStatus(@Param("ids")List<Long> ids,@Param("status") Integer status);

    /**
     * 解除取消订单的库存锁定
     */
    int releaseSkuStockLock(@Param("itemList") List<OmsOrderItem> orderItemList);
}
