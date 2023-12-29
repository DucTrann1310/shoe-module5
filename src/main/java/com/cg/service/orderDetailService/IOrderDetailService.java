package com.cg.service.orderDetailService;

import com.cg.model.OrderDetail;
import com.cg.service.IGeneralService;

import java.util.List;

public interface IOrderDetailService extends IGeneralService<OrderDetail, Long> {

    List<OrderDetail> findOrderDetailsByOrderId(Long order_id);
}
