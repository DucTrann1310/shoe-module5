package com.cg.controller.rest;

import com.cg.model.Price;
import com.cg.service.priceService.IPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shoe-ecommerce/price")
public class PriceAPI {

    @Autowired
    private IPriceService priceService;

    @GetMapping
    private ResponseEntity<?> findAllPrice(){

        List<Price> priceList = priceService.findAll();

        return new ResponseEntity<>(priceList, HttpStatus.OK);
    }
}
