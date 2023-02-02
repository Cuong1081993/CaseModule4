package com.example.bakery.service.productMedia;

import com.example.bakery.model.Product;
import com.example.bakery.model.ProductMedia;
import com.example.bakery.repository.ProductMediaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductMediaService implements IProductMediaService {

    private final ProductMediaRepository productMediaRepository;

    public ProductMediaService(ProductMediaRepository productMediaRepository) {
        this.productMediaRepository = productMediaRepository;
    }


    @Override
    public List<ProductMedia> findAll() {
        return null;
    }

    @Override
    public Optional<ProductMedia> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public ProductMedia save(ProductMedia productMedia) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void delete(ProductMedia productMedia) {

    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Optional<ProductMedia> findByProduct(Product product) {
        return productMediaRepository.findByProduct(product);
    }
}
