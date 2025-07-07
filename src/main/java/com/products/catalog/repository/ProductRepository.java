package com.products.catalog.repository;

import com.products.catalog.model.IBrandCount;
import com.products.catalog.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing Product entities
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Finds a product by its unique key
     *
     * @param productKey the unique identifier of the product
     * @return an Optional containing the product if found, or empty if not found
     */
    Optional<Product> findByProductKey(long productKey);

    /**
     * Retrieves a count of the number of products belonging to each unique brand
     *
     * @return a list of IBrandCount objects, each containing a brand name and its product count
     */
    @Query(value = "SELECT brand, COUNT(*) from product GROUP BY brand", nativeQuery = true)
    List<IBrandCount> getBrandCount();

}
