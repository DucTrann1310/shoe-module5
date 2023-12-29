package com.cg.model.dto;

import com.cg.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductCreReqDTO {

    private String title;

    private Long star;

    private Long reviews;

    private String prevPrice;

    private String newPrice;

    private String company;

    private String color;

    private String category;

    private MultipartFile file;

    public Product toProduct(Company company, Color color, Category category, ProductAvatar productAvatar){
        return new Product()
                .setTitle(title)
                .setStar(star)
                .setReviews(reviews)
                .setPrevPrice(BigDecimal.valueOf(Long.parseLong(prevPrice)))
                .setNewPrice(BigDecimal.valueOf(Long.parseLong(newPrice)))
                .setCompany(company)
                .setColor(color)
                .setCategory(category)
                .setAvatar(productAvatar);
    }
}
