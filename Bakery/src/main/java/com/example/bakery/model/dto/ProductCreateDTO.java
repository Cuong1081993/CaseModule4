package com.example.bakery.model.dto;

import com.example.bakery.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ProductCreateDTO implements Validator {

    private Long id;

    private String title;
    private String price;
    private Integer category;
    private String description;
    private MultipartFile file;

    public Product toProduct(){
        return new Product()
                .setId(id)
                .setTitle(title)
                .setPrice(BigDecimal.valueOf(Long.parseLong(price)))
                .setDescription(description);

    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductCreateDTO productCreateDTO = (ProductCreateDTO) target;
        String price = productCreateDTO.getPrice();

        if (price.length() == 0) {
            errors.rejectValue("price", "price.length", "Price not empty");
        }

        if (price.length() > 11 || price.length() < 2) {
            errors.rejectValue("price", "price.length", "Price size must be between 2 and 11");
        }
    }
}
