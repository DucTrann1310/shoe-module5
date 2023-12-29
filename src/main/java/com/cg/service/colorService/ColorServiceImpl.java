package com.cg.service.colorService;

import com.cg.model.Color;
import com.cg.repository.ColorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ColorServiceImpl implements IColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public List<Color> findAll() {
        return colorRepository.findAll();
    }

    @Override
    public Optional<Color> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Color color) {

    }

    @Override
    public void update(Color color) {

    }

    @Override
    public Color findColorByName(String name) {
        return colorRepository.findColorByName(name);
    }
}
