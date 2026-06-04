package com.ecommerce.productservice.dto;

import java.io.Serializable;

public class ProductResponse implements Serializable {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String category;

    public ProductResponse() {}

    public ProductResponse(Long id, String name, String description,
                           Double price, Integer stock, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Double getPrice() { return price; }
    public Integer getStock() { return stock; }
    public String getCategory() { return category; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(Double price) { this.price = price; }
    public void setStock(Integer stock) { this.stock = stock; }
    public void setCategory(String category) { this.category = category; }

    // Builder
    public static ProductResponseBuilder builder() { return new ProductResponseBuilder(); }

    public static class ProductResponseBuilder {
        private Long id;
        private String name;
        private String description;
        private Double price;
        private Integer stock;
        private String category;

        public ProductResponseBuilder id(Long id) { this.id = id; return this; }
        public ProductResponseBuilder name(String name) { this.name = name; return this; }
        public ProductResponseBuilder description(String d) { this.description = d; return this; }
        public ProductResponseBuilder price(Double price) { this.price = price; return this; }
        public ProductResponseBuilder stock(Integer stock) { this.stock = stock; return this; }
        public ProductResponseBuilder category(String c) { this.category = c; return this; }

        public ProductResponse build() {
            return new ProductResponse(id, name, description, price, stock, category);
        }
    }
}