package com.cg.service.cartDetailService;


import com.cg.model.*;
import com.cg.model.dto.AddToCartCreReqDTO;
import com.cg.model.dto.CustomerReqDTO;
import com.cg.repository.*;
//import com.cg.repository.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartDetailServiceImpl implements ICartDetailService {

    @Autowired
    private CartDetailRepository cartDetailRepository;

//    @Autowired
//    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<CartDetail> findAll() {
        return cartDetailRepository.findAll();
    }

    @Override
    public Optional<CartDetail> findById(Long id) {
        return cartDetailRepository.findById(id);
    }

    @Override
    public void save(CartDetail cartDetail) {

    }

    @Override
    public void update(CartDetail cartDetail) {

    }

    @Override
    public void create(AddToCartCreReqDTO addToCartCreReqDTO) {

//        Cart cart = cartRepository.findCartByMaxId();
        Product product = productRepository.findById(Long.valueOf(addToCartCreReqDTO.getProductId())).get();

//        if (cart == null) {
//            cart = new Cart();
//            cart.setSubtotal(product.getNewPrice());
//            cartRepository.save(cart);
//
//            CartDetail cartDetail = new CartDetail();
//
//
//            cartDetail.setProduct(product);
//            cartDetail.setQuantity(1L);
//            cartDetail.setCart(cart);
//
//            cartDetailDepository.save(cartDetail);
//
//        } else if (cart != null) {

            CartDetail cartDetail = cartDetailRepository.findCartDetailByProduct(product);

            if (cartDetail == null) {
                CartDetail newCartDetail = new CartDetail();
                newCartDetail.setProduct(product);
                newCartDetail.setQuantity(1L);
//                newCartDetail.setCart(cart);
                cartDetailRepository.save(newCartDetail);

//                cart.setSubtotal(cart.getSubtotal().add(product.getNewPrice()));
//                cartRepository.save(cart);

            }else if(cartDetail != null){

                cartDetail.setQuantity(cartDetail.getQuantity() + 1);
                cartDetailRepository.save(cartDetail);

//                cart.setSubtotal(cart.getSubtotal().add(product.getNewPrice()));
//                cartRepository.save(cart);
            }

//        }
    }

    @Override
    public void increQuantityProduct(Long cardDetailId) {
        CartDetail cartDetail = cartDetailRepository.findById(cardDetailId).get();
        cartDetail.setQuantity(cartDetail.getQuantity() + 1);
        cartDetailRepository.save(cartDetail);
    }

    @Override
    public void decreQuantityProduct(Long cardDetailId) {
        CartDetail cartDetail = cartDetailRepository.findById(cardDetailId).get();
        cartDetail.setQuantity(cartDetail.getQuantity() - 1);
        cartDetailRepository.save(cartDetail);
    }

    @Override
    public void removeCartDetail(Long cardDetailId) {
        cartDetailRepository.deleteById(cardDetailId);
    }

    @Override
    public void order(CustomerReqDTO customerReqDTO) {
        Order order = new Order();
        order.setCustomer( customerRepository.save(customerReqDTO.toCustomer()));
        order.setSubtotal(BigDecimal.ZERO);
        order.setTotalProducts(0L);
        orderRepository.save(order);
        Long totalProduct = 0L;
        BigDecimal subtotal = BigDecimal.ZERO;
        List<CartDetail> cartDetailList = cartDetailRepository.findAll();
        for(CartDetail cd : cartDetailList){
            totalProduct += 1;
            subtotal =  subtotal.add(cd.getProduct().getNewPrice().multiply(BigDecimal.valueOf(cd.getQuantity())));
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setQuantity(cd.getQuantity());
            orderDetail.setProduct(cd.getProduct());
            orderDetailRepository.save(orderDetail);
            cartDetailRepository.delete(cd);
        }
        order.setSubtotal(subtotal);
        order.setTotalProducts(totalProduct);
        orderRepository.save(order);
    }

}
