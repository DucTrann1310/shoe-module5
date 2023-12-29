package com.cg.repository;

//import com.cg.model.Cart;
import com.cg.model.CartDetail;
import com.cg.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {

    CartDetail findCartDetailByProduct(Product product);


}
