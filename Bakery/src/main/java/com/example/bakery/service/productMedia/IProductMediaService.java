package com.example.bakery.service.productMedia;

import com.example.bakery.model.Product;
import com.example.bakery.model.ProductMedia;
import com.example.bakery.service.IGeneralService;

import java.util.Optional;

public interface IProductMediaService extends IGeneralService<ProductMedia> {
 Optional<ProductMedia> findByProduct(Product product);
}
