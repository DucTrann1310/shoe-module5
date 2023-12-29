package com.cg.service.orderDetailService;

import com.cg.model.OrderDetail;
import com.cg.repository.OrderDetailRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class OrderDetailServiceImpl implements IOrderDetailService{

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public Optional<OrderDetail> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }

    @Override
    public void update(OrderDetail orderDetail) {

    }

    @Override
    public List<OrderDetail> findOrderDetailsByOrderId(Long order_id) {
        return orderDetailRepository.findOrderDetailsByOrderId(order_id);
    }
}
