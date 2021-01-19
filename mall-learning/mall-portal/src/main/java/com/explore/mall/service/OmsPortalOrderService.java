package com.explore.mall.service;

import com.explore.mall.domain.OrderParam;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 前台订单管理Service
 * create by liaoxinrong 2021/1/19
 */
public interface OmsPortalOrderService {

    /**
     * 自动取消超时订单
     */
    @Transactional
    Integer cancelTimeOutOrder();


    /**
     * 根据提交信息生成订单
     */
    @Transactional
    Map<String,Object> generateOrder(OrderParam orderParam);
}
