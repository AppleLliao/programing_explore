package com.explore.mall.domain;

import com.explore.mall.model.OmsOrder;
import com.explore.mall.model.OmsOrderItem;

import java.util.List;

public class OmsOrderDetail extends OmsOrder {
    private List<OmsOrderItem> orderItemList;

    public List<OmsOrderItem> getOrderItemList(){return  orderItemList;}

    public void setOrderItemList(List<OmsOrderItem> orderItemList){
        this.orderItemList = orderItemList;
    }
}
