package com.example.bakery.model.dto;


import com.example.bakery.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ProductResponseDTO {

    private Long id;
    private String title;
    private BigDecimal price;
    private Long quantity;
    private String description;
    private String category;

    private String mediaId;
    private String fileName;
    private String fileFolder;
    private String fileUrl;

    public ProductResponseDTO(Long id, String title, BigDecimal price, Long quantity, String description, String category) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.category = category;
    }
}
