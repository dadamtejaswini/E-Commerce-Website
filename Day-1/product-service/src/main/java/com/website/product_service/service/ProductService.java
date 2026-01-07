package com.website.product_service.service;

import com.website.product_service.dto.ProductRequest;
import com.website.product_service.dto.ProductResponse;
import com.website.product_service.entity.Product;
import com.website.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){
        Product product=Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("product saved successfully with {}",product.getId());
    }

    public List<ProductResponse> getAllProducts(){
        List<Product> productList=productRepository.findAll();
        return  productList.stream().map(this::mapToProductResponse).toList();
    }

    public ProductResponse getProductById(int id){
        Product product =  productRepository.getById(id);
        return mapToProductResponse(product);
    }

    private ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
