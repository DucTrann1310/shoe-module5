package com.cg.controller.rest;

import com.cg.model.Company;
import com.cg.service.companyService.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shoe-ecommerce/company")
public class ComapnyAPI {

    @Autowired
    private ICompanyService companyService;

    @GetMapping
    public ResponseEntity<?> getAllCompany(){

        List<Company> companyList = companyService.findAll();

        return new ResponseEntity<>(companyList, HttpStatus.OK);
    }
}
