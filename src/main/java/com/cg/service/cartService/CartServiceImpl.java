//package com.cg.service.cartService;
//
//import com.cg.model.Cart;
//import com.cg.repository.CartRepository;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@Transactional
//public class CartServiceImpl implements ICartService{
//
//    @Autowired
//    private CartRepository cartRepository;
//
//    @Override
//    public List<Cart> findAll() {
//        return cartRepository.findAll();
//    }
//
//    @Override
//    public Optional<Cart> findById(Long id) {
//        return Optional.empty();
//    }
//
//    @Override
//    public void save(Cart cart) {
//
//    }
//
//    @Override
//    public void update(Cart cart) {
//
//    }
//}
