package com.example.bakery.repository;

import com.example.bakery.model.Product;
import com.example.bakery.model.dto.ProductResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT NEW com.example.bakery.model.dto.ProductResponseDTO(" +
            "p.id, " +
            "p.title, " +
            "p.price, " +
            "p.quantity, " +
            "p.description, " +
            "p.category.id, " +
            "p.category.typeProduct, " +
            "pm.id, " +
            "pm.fileName, " +
            "pm.fileFolder, " +
            "pm.fileUrl " +
            ") " +
            "FROM Product AS p " +
            "JOIN ProductMedia AS pm " +
            "ON pm.product = p " +
            "AND p.deleted = FALSE "
    )
    List<ProductResponseDTO> findAllProductDeleteFalse();

    List<Product> findAllByDeletedIsFalse();

    @Modifying
    @Query("UPDATE Product AS p SET p.deleted = TRUE WHERE p.id = :productId")
    void softDelete(@Param("productId") Long productId);

    @Query("SELECT NEW com.example.bakery.model.dto.ProductResponseDTO(" +
            "p.id, " +
            "p.title, " +
            "p.price, " +
            "p.quantity, " +
            "p.description, " +
            "p.category.id, " +
            "p.category.typeProduct, " +
            "pm.id, " +
            "pm.fileName, " +
            "pm.fileFolder, " +
            "pm.fileUrl " +
            ") " +
            "FROM Product AS p " +
            "JOIN ProductMedia AS pm " +
            "ON pm.product = p " +
            "AND p.deleted = FALSE "+
            "WHERE p.title LIKE %:stringSearch%"


    )
    List<ProductResponseDTO> findAlByTitleContaining(@Param("stringSearch") String stringSearch);
}
