package com.example.bakery.model;

import com.example.bakery.model.dto.ProductCreateDTO;
import com.example.bakery.model.dto.ProductResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
@Accessors(chain = true)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(precision = 9, scale = 0, nullable = false)
    private BigDecimal price;

    private Long quantity;

    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_category", nullable = false)
    private Category category;

    public ProductResponseDTO toDTO() {

        return new ProductResponseDTO()
                .setId(id)
                .setTitle(title)
                .setPrice(price)
                .setQuantity(quantity)
                .setDescription(description)
                .setCategory(category.getTypeProduct());
    }

    public ProductResponseDTO toProductResponseDTO(ProductMedia productMedia) {
        return new ProductResponseDTO()
                .setId(id)
                .setTitle(title)
                .setPrice(price)
                .setQuantity(quantity)
                .setDescription(description)
                .setCategoryId(category.getId())
                .setCategory(category.getTypeProduct())
                .setMediaId(productMedia.getId())
                .setFileName(productMedia.getFileName())
                .setFileFolder(productMedia.getFileFolder())
                .setFileUrl(productMedia.getFileUrl())
                ;

    }
}
