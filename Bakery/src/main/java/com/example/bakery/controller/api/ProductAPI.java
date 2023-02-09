package com.example.bakery.controller.api;


import com.example.bakery.exception.DataInputException;
import com.example.bakery.model.Category;
import com.example.bakery.model.Product;
import com.example.bakery.model.ProductMedia;
import com.example.bakery.model.dto.ProductCreateDTO;
import com.example.bakery.model.dto.ProductEditDTO;
import com.example.bakery.model.dto.ProductResponseDTO;
import com.example.bakery.repository.CategoryRepository;
import com.example.bakery.repository.ProductMediaRepository;
import com.example.bakery.service.product.IProductService;
import com.example.bakery.service.productMedia.IProductMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/products")
public class ProductAPI {

    @Autowired
    private IProductService productService;

    @Autowired
    private IProductMediaService productMediaService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductMediaRepository productMediaRepository;


    @GetMapping
    public ResponseEntity<?> getAll() {
        List<ProductResponseDTO> productResponseDTOS = productService.findAllProductDeleteFalse();
        return new ResponseEntity<>(productResponseDTOS, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(ProductCreateDTO productCreateDTO, BindingResult bindingResult) {
        productCreateDTO.setId(null);

        Product newProduct = productService.create(productCreateDTO);
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        Optional<ProductMedia> productMediaOptional = productMediaService.findByProduct(newProduct);

        if (!productMediaOptional.isPresent()) {
            ProductMedia productMedia = new ProductMedia();
            productMedia.setId(null);
            productMedia.setFileName(null);
            productMedia.setFileFolder(null);
            productMedia.setFileUrl(null);
            productResponseDTO = newProduct.toProductResponseDTO(productMedia);
        } else {
            ProductMedia productMedia = productMediaOptional.get();
            productResponseDTO = newProduct.toProductResponseDTO(productMedia);
        }
        return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<?> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{idDelete}")
    public ResponseEntity<?> findProductById(@PathVariable Long idDelete) {
        Optional<Product> product = productService.findById(idDelete);
        System.out.println(product.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        System.out.println(productOptional);
        if (!productOptional.isPresent()) {
            throw new DataInputException("Product invalid");
        }
        productService.softDelete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            throw new DataInputException("Product invalid");
        }

        Product product = productOptional.get();

        Optional<ProductMedia> productMediaOptional = productMediaService.findByProduct(product);

        ProductResponseDTO productResponseDTO = product.toProductResponseDTO(productMediaOptional.get());
        return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/update/{idEdit}")
    public ResponseEntity<?> findEditProductById(@PathVariable Long idEdit) {
        Optional<Product> productOptional = productService.findById(idEdit);
        Product product = productOptional.get();
        Optional<ProductMedia> productMedia = productMediaService.findByProduct(product);
        return new ResponseEntity<>(productMedia.get(), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> doUpdate(@ModelAttribute ProductEditDTO productEditDto) {
        Optional<Product> productOptional = productService.findById(productEditDto.getId());

        if (productOptional == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Product newProduct = productService.updateProduct(productEditDto);

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        Optional<ProductMedia> productMediaOptional = productMediaService.findByProduct(newProduct);

        if (!productMediaOptional.isPresent()) {
            ProductMedia productMedia = new ProductMedia();
            productMedia.setId(null);
            productMedia.setFileName(null);
            productMedia.setFileFolder(null);
            productMedia.setFileUrl(null);
            productResponseDTO = newProduct.toProductResponseDTO(productMedia);
        } else {
            ProductMedia productMedia = productMediaOptional.get();
            productResponseDTO = newProduct.toProductResponseDTO(productMedia);
        }

        return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
    }
}
