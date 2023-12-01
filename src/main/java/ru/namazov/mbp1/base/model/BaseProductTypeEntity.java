/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.base.model;

import ru.namazov.mbp1.nomenclature.model.Product;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Getter;

@MappedSuperclass
@Data
@Getter
public class BaseProductTypeEntity extends BaseEntity{

    @Column(name = "count")
    private long count;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public String getProductName() {
        return product.getName();
    }
}
