package com.cg.service.priceService;

import com.cg.model.Price;
import com.cg.repository.PriceRepository;
import com.cg.service.productService.ProductServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PriceServiceImpl implements IPriceService{
    @Autowired
    private PriceRepository priceRepository;

    @Override
    public List<Price> findAll() {
        return priceRepository.findAll();
    }

    @Override
    public Optional<Price> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Price price) {

    }

    @Override
    public void update(Price price) {

    }
}
