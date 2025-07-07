package com.products.catalog;

import com.products.catalog.model.BrandCount;
import com.products.catalog.model.IBrandCount;
import com.products.catalog.model.Product;
import com.products.catalog.repository.ProductRepository;
import com.products.catalog.service.ProductService;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductService();
        productService.productRepository = productRepository;
    }

    @Test
    void testGetAllProducts() {
        Product p1 = new Product(); p1.setProductKey(1L);
        Product p2 = new Product(); p2.setProductKey(2L);

        when(productRepository.findAll()).thenReturn(List.of(p1, p2));

        List<Product> result = productService.getAllProducts();
        assertEquals(2, result.size());
    }

    @Test
    void testAddProduct() {
        Product input = new Product(); input.setProductKey(100L);
        when(productRepository.save(input)).thenReturn(input);

        Product result = productService.addProduct(input);
        assertEquals(100L, result.getProductKey());
        verify(productRepository).save(input);
    }

    @Test
    void testGetProductByID_Found() {
        Product product = new Product(); product.setProductKey(10L);
        when(productRepository.findByProductKey(10L)).thenReturn(Optional.of(product));

        Product result = productService.getProductByID(10L);
        assertEquals(10L, result.getProductKey());
    }

    @Test
    void testGetProductByID_NotFound() {
        when(productRepository.findByProductKey(999L)).thenReturn(Optional.empty());

        ObjectNotFoundException thrown = assertThrows(ObjectNotFoundException.class, () -> {
            productService.getProductByID(999L);
        });

        assertTrue(thrown.getMessage().contains("Unable to locate product with ID"));
    }

    @Test
    void testGetBrandCount() {
        BrandCount brandCount = new BrandCount("GIANT ART", 3);
        when(productRepository.getBrandCount()).thenReturn(List.of(brandCount));

        List<IBrandCount> result = productService.getBrandCount();

        assertEquals(1, result.size());
        assertEquals("GIANT ART", result.get(0).getBrand());
        assertEquals(3L, result.get(0).getCount());
    }
}