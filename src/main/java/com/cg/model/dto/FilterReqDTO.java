package com.cg.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FilterReqDTO {


    private String input = "";

    private BigDecimal minPrice = BigDecimal.ZERO;

    private BigDecimal maxPrice = BigDecimal.ZERO;

    private String company = "all products";

    private String color = "all";

    private String category = "all";
}
