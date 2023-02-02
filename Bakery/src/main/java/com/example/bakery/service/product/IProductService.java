package com.example.bakery.service.product;

import com.example.bakery.model.Product;
import com.example.bakery.model.dto.ProductCreateDTO;
import com.example.bakery.model.dto.ProductEditDTO;
import com.example.bakery.model.dto.ProductResponseDTO;

import java.util.List;

public interface IProductService extends com.cg.service.IGeneralService<Product> {

    public List<ProductResponseDTO> findAllProduct();

    Product create(ProductCreateDTO productCreateDto);

    void softDelete(long productId);

    List<ProductResponseDTO> findAllProductDeleteFalse();

    Product updateProduct(ProductEditDTO productEditDto);

    List<ProductResponseDTO> search(String query);
}
