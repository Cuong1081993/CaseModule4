package com.example.bakery.service.product;

import com.example.bakery.repository.CategoryRepository;
import com.example.bakery.repository.ProductMediaRepository;
import com.example.bakery.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductService implements IProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMediaRepository productMediaRepository;

    @Autowired
    private ProductMediaRepository mediaRepository;

    @Autowired
    private IUploadService uploadService;

    @Autowired
    private UploadUtil uploadUtil;

    @Autowired
    private CategoryRepository categoryRepository;
}
