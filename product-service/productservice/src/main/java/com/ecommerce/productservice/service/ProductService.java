package com.ecommerce.productservice.service;

import com.ecommerce.productservice.dto.*;
import com.ecommerce.productservice.entity.Product;
import com.ecommerce.productservice.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    // Manual logger - replaces @Slf4j
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Cacheable(value = "products", key = "'all'")
    public List<ProductResponse> getAllProducts() {
        log.debug("Fetching all products from DATABASE");
        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Cacheable(value = "products", key = "#id")
    public ProductResponse getProductById(Long id) {
        log.debug("Fetching product {} from DATABASE", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return mapToResponse(product);
    }

    @Cacheable(value = "products", key = "'category_' + #category")
    public List<ProductResponse> getProductsByCategory(String category) {
        return productRepository.findByCategory(category)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @CacheEvict(value = "products", key = "'all'")
    public ProductResponse createProduct(ProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .category(request.getCategory())
                .build();

        Product saved = productRepository.save(product);
        log.debug("Product created: {}", saved.getId());
        return mapToResponse(saved);
    }

    @Caching(evict = {
        @CacheEvict(value = "products", key = "#id"),
        @CacheEvict(value = "products", key = "'all'")
    })
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setCategory(request.getCategory());

        return mapToResponse(productRepository.save(product));
    }

    @Caching(evict = {
        @CacheEvict(value = "products", key = "#id"),
        @CacheEvict(value = "products", key = "'all'")
    })
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
        log.debug("Product deleted: {}", id);
    }

    public void reduceStock(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStock() < quantity) {
            throw new RuntimeException("Insufficient stock for product: " + productId);
        }

        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
    }

    private ProductResponse mapToResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .category(product.getCategory())
                .build();
    }
}