package com.cg.controller.rest;

import com.cg.model.CartDetail;
import com.cg.model.Order;
import com.cg.model.OrderDetail;
import com.cg.model.Product;
import com.cg.model.dto.*;
import com.cg.repository.OrderRepository;
import com.cg.repository.ProductRepository;
import com.cg.service.cartDetailService.ICartDetailService;
import com.cg.service.companyService.ICompanyService;
import com.cg.service.orderDetailService.IOrderDetailService;
import com.cg.service.orderService.IOrderService;
import com.cg.service.productService.IProductService;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/shoe-ecommerce")
public class ProductAPI {

    @Autowired
    private IProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ICartDetailService cartDetailService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderDetailService orderDetailService;

    @GetMapping
    private ResponseEntity<?> getAllProduct(){

        List<ProductResDTO> productResDTOList = productService.findAllProductResDTO();

        return new ResponseEntity<>(productResDTOList, HttpStatus.OK);
    }

    @GetMapping("/cartDetail")
    private ResponseEntity<?> getCartDetail(){
        List<CartDetail> cartDetailList = cartDetailService.findAll();
        return new ResponseEntity<>(cartDetailList, HttpStatus.OK);
    }

    @GetMapping("/filter")
    private ResponseEntity<?> filterProduct(FilterReqDTO filterReqDTO, @PageableDefault(size = 5) Pageable pageable){
        return new ResponseEntity<>(productService.filter(filterReqDTO, pageable), HttpStatus.OK);
    }

    @GetMapping("/order-list/orders")
    private ResponseEntity<?> getAllOrders(){
        List<Order> orderList = orderService.findAll();
        return new ResponseEntity<>(orderList,HttpStatus.OK);
    }

    @GetMapping("/order-list/order/{orderId}")
    private ResponseEntity<?> getOrderByOrderId(@PathVariable Long orderId){
        Order order = orderService.findById(orderId).get();
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @GetMapping("/order-list/orderDetail/{orderId}")
    private ResponseEntity<?> getOrderDetailList(@PathVariable Long orderId){
        List<OrderDetail> orderDetailList = orderDetailService.findOrderDetailsByOrderId(orderId);
        return new ResponseEntity<>(orderDetailList,HttpStatus.OK);
    }

    @GetMapping("/product-list")

    @PostMapping
    private ResponseEntity<?> createProduct(@RequestBody ProductCreReqDTO productCreReqDTO){

        productService.create(productCreReqDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/addToCart")
    private ResponseEntity<?> addToCart (@RequestBody AddToCartCreReqDTO addToCartCreReqDTO){
        cartDetailService.create(addToCartCreReqDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/increQuantity/{cardDetailId}")
    private ResponseEntity<?> increQuantityProduct(@PathVariable Long cardDetailId){
        cartDetailService.increQuantityProduct(cardDetailId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/decreQuantity/{cardDetailId}")
    private ResponseEntity<?> decreQuantityProduct(@PathVariable Long cardDetailId){
        cartDetailService.decreQuantityProduct(cardDetailId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/removeCartDetail/{cardDetailId}")
    private ResponseEntity<?> removeCartDetail(@PathVariable Long cardDetailId){
        cartDetailService.removeCartDetail(cardDetailId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/checkout")
    private ResponseEntity<?> checkout(@RequestBody CustomerReqDTO customerReqDTO){
        cartDetailService.order(customerReqDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
