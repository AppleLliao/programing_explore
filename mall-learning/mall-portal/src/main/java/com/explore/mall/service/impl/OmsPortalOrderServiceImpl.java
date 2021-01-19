package com.explore.mall.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.explore.mall.dao.PortalOrderDao;
import com.explore.mall.domain.OmsOrderDetail;
import com.explore.mall.domain.OrderParam;
import com.explore.mall.mapper.OmsOrderSettingMapper;
import com.explore.mall.model.OmsOrderSetting;
import com.explore.mall.service.OmsPortalOrderService;
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

    @Autowired
    PortalOrderDao portalOrderDao;

    @Autowired
    OmsOrderSettingMapper orderSettingMapper;
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


    @Override
    public Map<String, Object> generateOrder(OrderParam orderParam) {
        return null;
    }
}
