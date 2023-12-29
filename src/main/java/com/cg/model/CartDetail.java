package com.cg.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cart_details")
public class CartDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;


    private Long quantity;

    public OrderDetail toOrderDetail(){
        return new OrderDetail()
                .setProduct(product)
                .setQuantity(quantity);
    }

//    @ManyToOne
//    @JoinColumn(name = "cart_id", referencedColumnName = "id")
//    private Cart cart;

}
