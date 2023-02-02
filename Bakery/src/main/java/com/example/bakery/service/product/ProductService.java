package com.example.bakery.service.product;

import com.example.bakery.exception.DataInputException;
import com.example.bakery.model.Category;
import com.example.bakery.model.Product;
import com.example.bakery.model.ProductMedia;
import com.example.bakery.model.dto.ProductCreateDTO;
import com.example.bakery.model.dto.ProductEditDTO;
import com.example.bakery.model.dto.ProductResponseDTO;
import com.example.bakery.repository.CategoryRepository;
import com.example.bakery.repository.ProductMediaRepository;
import com.example.bakery.repository.ProductRepository;
import com.example.bakery.service.upload.IUploadService;
import com.example.bakery.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@Transactional
public class ProductService implements IProductService {

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


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void delete(Product product) {

    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public List<ProductResponseDTO> findAllProduct() {
        List<Product> productList = productRepository.findAllByDeletedIsFalse();
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();

        for (Product product : productList) {
            productResponseDTOS.add(product.toProductResponseDTO(mediaRepository.findByProduct(product).get()));
        }
        return productResponseDTOS;
    }

    @Override
    public Product create(ProductCreateDTO productCreateDto) {
        Product product = productCreateDto.toProduct();
        Optional<Category> categoryOptional = categoryRepository.findAllById(Long.valueOf(productCreateDto.getCategory()));

        if (!categoryOptional.isPresent()) {
            throw new DataInputException("Category invalid");
        }
        product.setCategory(categoryOptional.get());
        product.setId(null);
        product.setQuantity(0L);
        productRepository.save(product);

        String fileType = productCreateDto.getFile().getContentType();

        assert fileType != null;

        fileType = fileType.substring(0, 5);

        ProductMedia productMedia = new ProductMedia();
        productMedia.setId(null);
        productMedia.setFileType(fileType);
        productMediaRepository.save(productMedia);

        uploadAndSaveProductImage(productCreateDto, product, productMedia);

        return product;
    }

    private void uploadAndSaveProductImage(ProductCreateDTO productCreateDto, Product product, ProductMedia productMedia) {
        try {
            Map util = uploadUtil.buildImageUploadParams(productMedia);
            MultipartFile file = productCreateDto.getFile();
            Map uploadResult = uploadService.uploadImage(file, util);
            System.out.println(uploadResult);
            String fileUrl = (String) uploadResult.get("secure_url");
            String fileFormat = (String) uploadResult.get("format");

            productMedia.setFileName(productMedia.getId() + "." + fileFormat);
            productMedia.setFileUrl(fileUrl);
            productMedia.setFileFolder(uploadUtil.IMAGE_UPLOAD_FOLDER);
            productMedia.setCloudId(productMedia.getFileFolder() + "/" + productMedia.getId());
            productMedia.setProduct(product);
            productMediaRepository.save(productMedia);

        } catch (IOException e) {
            e.printStackTrace();
            throw new DataInputException("khong tim thay");
        }
    }

    @Override
    public void softDelete(long productId) {
        productRepository.softDelete(productId);
    }

    @Override
    public List<ProductResponseDTO> findAllProductDeleteFalse() {
        return null;
    }

    @Override
    public Product updateProduct(ProductEditDTO productEditDto) {

        Product product = productEditDto.toProduct();
        Optional<Category> categoryOptional = categoryRepository.findAllById(Long.valueOf(productEditDto.getCategory()));

        if (!categoryOptional.isPresent()) {
            throw new DataInputException("Category invalid");
        }

        product.setCategory(categoryOptional.get());
        product.setId(productEditDto.getId());
        product.setQuantity(0L);
        productRepository.save(product);

        String fileType = productEditDto.getFile().getContentType();

        assert fileType != null;


        fileType = fileType.substring(0, 5);
        ProductMedia productMedia = mediaRepository.findByProduct(product).get();
//        productMedia.setId(null);
        productMedia.setFileType(fileType);
        productMediaRepository.save(productMedia);
        uploadAndSaveEditProductImage(productEditDto, product, productMedia);

        return product;
    }

    private void uploadAndSaveEditProductImage(ProductEditDTO productEditDto, Product product, ProductMedia productMedia) {
        try {
            Map util = uploadUtil.buildImageUploadParams(productMedia);
            MultipartFile file = productEditDto.getFile();
            Map uploadResult = uploadService.uploadImage(file, util);
            System.out.println(uploadResult);
            String fileUrl = (String) uploadResult.get("secure_url");
            String fileFormat = (String) uploadResult.get("format");

            productMedia.setFileName(productMedia.getId() + "." + fileFormat);
            productMedia.setFileUrl(fileUrl);
            productMedia.setFileFolder(uploadUtil.IMAGE_UPLOAD_FOLDER);
            productMedia.setCloudId(productMedia.getFileFolder() + "/" + productMedia.getId());
            productMedia.setProduct(product);
            productMediaRepository.save(productMedia);

        } catch (IOException e) {
            e.printStackTrace();
            throw new DataInputException("khong tim thay");
        }
    }


    @Override
    public List<ProductResponseDTO> search(String query) {
        return null;
    }
}
