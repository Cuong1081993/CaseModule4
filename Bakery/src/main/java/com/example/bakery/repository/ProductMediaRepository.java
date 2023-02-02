package com.example.bakery.repository;

import com.example.bakery.model.Product;
import com.example.bakery.model.ProductMedia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductMediaRepository extends JpaRepository<ProductMedia, Long> {
    Optional<ProductMedia> findAllByProduct(Product product);
}
