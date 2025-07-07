package com.products.catalog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity used to provide a report on the count of products which belong to each brand in the catalog
 */
@Entity
@Table(name = "product", schema = "public")
public class BrandCount implements IBrandCount{

    @Id
    @Column(name = "brand")
    private String brand;

    @Column(name = "count")
    private long count;

    public BrandCount(String brand, long count) {
        this.brand = brand;
        this.count = count;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BrandCount obj)) {
            return false;
        }
        if (o == this) {
            return true;
        } else return obj.getBrand().equals(this.getBrand());
    }
}
