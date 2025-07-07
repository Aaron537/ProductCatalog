package com.products.catalog.model;

/**
 * Interface created to make use of Hibernate's interface-based projection
 * Simplifies the database interaction when querying against a subset of fields,
 * allowing the results to be easily mapped to an object
 */
public interface IBrandCount {
    String getBrand();
    long getCount();
}