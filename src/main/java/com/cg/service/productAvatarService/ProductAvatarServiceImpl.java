package com.cg.service.productAvatarService;

import com.cg.model.ProductAvatar;
import com.cg.repository.ProductAvatarRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductAvatarServiceImpl implements IProductAvatarService{

    @Autowired
    private ProductAvatarRepository productAvatarRepository;

    @Override
    public List<ProductAvatar> findAll() {
        return productAvatarRepository.findAll();
    }

    @Override
    public Optional<ProductAvatar> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(ProductAvatar productAvatar) {
        productAvatarRepository.save(productAvatar);
    }

    @Override
    public void update(ProductAvatar productAvatar) {

    }
}
