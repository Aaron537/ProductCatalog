package com.products.catalog.service;

import com.products.catalog.model.IBrandCount;
import com.products.catalog.model.Product;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.products.catalog.repository.ProductRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service for managing the product catalog
 * Handles business logic for interacting with the ProductRepository for database operations
 */
@Service
@Transactional
public class ProductService {

    /**
     * Repository for retrieving and persisting Product entities
     */
    @Autowired
    public ProductRepository productRepository;

    /**
     * Retrieves all products from the catalog.
     *
     * @return a list of all products in the database
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Adds a new product to the catalog.
     *
     * @param product the product to add
     * @return the saved product
     */
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Retrieves a product by its unique product key.
     *
     * @param id the unique product key
     * @return the product with the specified key
     * @throws ObjectNotFoundException if no product is found with the given key
     */
    public Product getProductByID(Long id) throws ObjectNotFoundException {
        Optional<Product> product = productRepository.findByProductKey(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new ObjectNotFoundException("Unable to locate product with ID: " + id, id);
        }
    }

    /**
     * Retrieves a list of all unique brands in the catalog, along with a count indicating the number
     * of entries using that brand
     *
     * @return a list of brand counts, where each entry contains a brand name and its product count
     */
    public List<IBrandCount> getBrandCount() {
        return productRepository.getBrandCount();
    }

}
