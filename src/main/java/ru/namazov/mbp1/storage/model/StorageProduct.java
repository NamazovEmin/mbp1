/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.storage.model;

import ru.namazov.mbp1.base.model.BaseEntity;
import ru.namazov.mbp1.nomenclature.model.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products_in_storages")
@NoArgsConstructor
@Setter
@Getter
public class StorageProduct extends BaseEntity {

    @Column(name = "count")
    private Long count;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinTable(name = "storage_id")
    private Storage storage;

    public String getProductName() {
        return product.getName();
    }

    public String getProductMeasurement() {
        return product.getMeasurement();
    }

    public String getProductDescription() {
        return product.getDescription();
    }
}
