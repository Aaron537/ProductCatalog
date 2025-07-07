package com.products.catalog.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

/**
 * Entity used to hold a single entry in the product catalog
 */
@Entity
@Table(name = "product", schema = "public")
public class Product {

    @Id
    @Column(name = "product_key", unique = true)
    @JsonProperty("product_key")
    private long productKey;

    @Column(name = "retailer")
    private String retailer;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "product_name")
    @JsonProperty("product_name")
    private String productName;

    @Lob
    @Column(name = "product_description", columnDefinition = "TEXT")
    @JsonProperty("product_description")
    private String productDescription;

    @Column(name = "price")
    private float price;

    public Product() {}

    public long getProductKey() {
        return productKey;
    }

    public void setProductKey(long productKey) {
        this.productKey = productKey;
    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
