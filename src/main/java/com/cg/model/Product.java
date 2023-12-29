package com.cg.model;

import com.cg.model.dto.ProductResDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
@Accessors(chain = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Long star;

    private Long reviews;

    @Column(name = "prev_price")
    private BigDecimal prevPrice;

    @Column(name = "new_price")
    private BigDecimal newPrice;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "color_id", referencedColumnName = "id")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @OneToOne
    @JoinColumn(name = "avatar_id", referencedColumnName = "id", nullable = false)
    private ProductAvatar avatar;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;


//    public ProductResDTO toProducResDTO(){
//        return new ProductResDTO()
//                .setId(id)
//                .setImg(img)
//                .setTitle(title)
//                .setStar(star)
//                .setPrevPrice(prevPrice)
//                .setNewPrice(newPrice)
//                .setCompany(company)
//                .setColor(color)
//                .setCategory(category);
//    }
}
