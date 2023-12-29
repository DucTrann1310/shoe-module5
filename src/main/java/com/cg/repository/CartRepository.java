//package com.cg.repository;
//
//import com.cg.model.Cart;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface CartRepository extends JpaRepository<Cart, Long> {
//
//
//    @Query(value =
//            "SELECT " +
//                    "* " +
//            "FROM carts " +
//            "WHERE id = (" +
//                    "SELECT " +
//                            "MAX(id) " +
//                    "FROM carts)",
//            nativeQuery = true
//    )
//    Cart findCartByMaxId();
//}
