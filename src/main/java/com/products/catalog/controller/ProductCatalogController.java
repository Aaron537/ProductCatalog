package com.products.catalog.controller;

import com.products.catalog.model.IBrandCount;
import com.products.catalog.model.Product;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.products.catalog.service.ProductService;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * REST controller for managing the productcatalog
 * Provides endpoints to retrieve, add, and report on products
 * Configured to allow cross-origin requests from the React frontend
 */
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/catalog")
public class ProductCatalogController {

    /**
     * Service for handling database interactions
     */
    @Autowired
    private ProductService productService;

    /**
     * Retrieves all products in the catalog
     *
     * @return a list of all products
     */
    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    /**
     * Adds a new product
     *
     * @param product the product to add, provided in the request body
     * @return the saved product
     */
    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    /**
     * Populates the catalog with a list of products
     *
     * @param products the list of products to add
     * @return a 200 OK response with a message indicating success
     */
    @PostMapping("/populateProducts")
    public ResponseEntity<String> populateProducts(@RequestBody List<Product> products) {
        for (Product iter : products) {
            productService.addProduct(iter);
        }
        return new ResponseEntity<>("Products successfully populated", HttpStatus.OK);
    }

    /**
     * Retrieves a product by its unique product key
     *
     * @param productKey the unique identifier of the product
     * @return the requested product
     * @throws ResponseStatusException if the requested product is not found
     */
    @GetMapping("/products/{productKey}")
    public Product getProductByID(@PathVariable("productKey") Long productKey) {
        try {
            return productService.getProductByID(productKey);
        } catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find product with ID: " + productKey);
        }
    }

    /**
     * Retrieves a count of all products, grouped by brand
     *
     * @return a list of brand counts, where each entry contains a brand name and its product count
     */
    @GetMapping("/products/brand-summary")
    public List<IBrandCount> getBrandCount() {
        return productService.getBrandCount();
    }
}
