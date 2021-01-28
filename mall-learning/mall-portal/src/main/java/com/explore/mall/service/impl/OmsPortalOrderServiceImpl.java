package com.explore.mall.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.explore.mall.api.CommonResult;
import com.explore.mall.component.CancelOrderSender;
import com.explore.mall.dao.PortalOrderDao;
import com.explore.mall.domain.OmsOrderDetail;
import com.explore.mall.domain.OrderParam;
import com.explore.mall.mapper.OmsOrderSettingMapper;
import com.explore.mall.model.OmsOrderSetting;
import com.explore.mall.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 前台订单管理Service
 * Created by xinrong on 2020/1/21
 */
@Service
public class OmsPortalOrderServiceImpl implements OmsPortalOrderService {
    private static Logger LOGGER = LoggerFactory.getLogger(OmsPortalOrderServiceImpl.class);
    @Autowired
    PortalOrderDao portalOrderDao;

    @Autowired
    OmsOrderSettingMapper orderSettingMapper;
    @Autowired
    private CancelOrderSender cancelOrderSender;

    @Override
    public CommonResult generateOrder(OrderParam orderParam) {
        //执行一系类下单操作
        //下单完成后开启一个延迟消息，用于当用户没有付款时取消订单（orderId应该在下单后生成）
        sendDelayMessageCancelOrder(122L);
        return CommonResult.success(null,"下单成功");
    }

    private void sendDelayMessageCancelOrder(Long orderId){
        //获取订单超时时间，假设为60分钟
        long delayTimes=30*1000;
        //发送延迟消息
        cancelOrderSender.sendMessage(orderId,delayTimes);
    }

    @Override
    public void cancelOrder(Long orderId) {
        //执行一系列类取消订单操作
        LOGGER.info("process cancelOrder orderId:{}",orderId);
    }



    @Override
    public Integer cancelTimeOutOrder() {
        Integer count=0;
        OmsOrderSetting orderSetting=orderSettingMapper.selectByPrimaryKey(1L);
        //查询超时，未支付的订单及订单详情
        List<OmsOrderDetail> timeOutOrders=portalOrderDao.getTimeOutOrders(orderSetting.getNormalOrderOvertime());
        if(CollectionUtils.isEmpty(timeOutOrders)){
            return count;
        }
        //修改订单状态为交易取消
        List<Long> ids = new ArrayList<>();
        for(OmsOrderDetail timeOutOrder:timeOutOrders){
            ids.add(timeOutOrder.getId());
        }
        portalOrderDao.updateOrderStatus(ids,4);
        for(OmsOrderDetail timeOutOrder:timeOutOrders){
            //解除订单商品库存锁定
            portalOrderDao.releaseSkuStockLock(timeOutOrder.getOrderItemList());
            //修改优惠卷使用状态
            //updateCouponStatus(Long couponId,Long member);
        }
        return timeOutOrders.size();
    }

    /**
     * 将优惠卷信息更改为指定状态
     * @param couponId
     * @param memberId
     * @param useStatus
     */
    public void updateCouponStatus(Long couponId,Long memberId,Integer useStatus){

    }



}
