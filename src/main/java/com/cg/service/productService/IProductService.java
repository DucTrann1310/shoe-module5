package com.cg.service.productService;

import com.cg.model.Product;
import com.cg.model.dto.FilterReqDTO;
import com.cg.model.dto.ProductCreReqDTO;
import com.cg.model.dto.ProductResDTO;
import com.cg.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductService extends IGeneralService<Product, Long> {

    void create(ProductCreReqDTO productCreReqDTO);

    List<ProductResDTO> findAllProductResDTO();


//    List<ProductResDTO> filter(FilterReqDTO filterReqDTO);

    Page<ProductResDTO> filter(FilterReqDTO filterReqDTO, Pageable pageable);


}
