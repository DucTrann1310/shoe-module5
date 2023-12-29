package com.cg.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shoe-ecommerce")
public class ProductController {

    @GetMapping
    private String showProduct() {
        return "/product/index";
    }

    @GetMapping("/cart")
    private String showCart() {
        return "/product/cart";
    }

    @GetMapping("/dashboard/order-list")
    private String showOrderListDashboard() {
        return "/product/dashboard/order-list";
    }

    @GetMapping("/dashboard/product-list")
    private String showProductListDashboard(){
        return "/product/dashboard/product-list";
    }
}
