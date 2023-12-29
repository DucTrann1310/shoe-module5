package com.cg.controller.rest;


import com.cg.model.Color;
import com.cg.service.colorService.IColorService;
import com.cg.service.companyService.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shoe-ecommerce/color")
public class ColorAPI {

    @Autowired
    private IColorService colorService;

    @GetMapping
    private ResponseEntity<?> getAllColors(){

        List<Color> colorList = colorService.findAll();

        return new ResponseEntity<>(colorList, HttpStatus.OK);
    }
}
