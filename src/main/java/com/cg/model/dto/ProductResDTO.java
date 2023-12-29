package com.cg.model.dto;

import com.cg.model.Category;
import com.cg.model.Color;
import com.cg.model.Company;
import com.cg.model.ProductAvatar;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ProductResDTO {

    private Long id;

    private String title;

    private Long star;

    private Long reviews;

    private BigDecimal prevPrice;

    private BigDecimal newPrice;

    private Company company;

    private Color color;

    private Category category;

    private ProductAvatar productAvatar;
}
