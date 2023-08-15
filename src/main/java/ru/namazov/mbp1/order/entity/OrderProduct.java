/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.order.entity;

import ru.namazov.mbp1.base.model.BaseEntity;
import ru.namazov.mbp1.nomenclature.model.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Setter;

@Entity
@Table(name = "product_in_order")
@Setter
public class OrderProduct extends BaseEntity {

    @ManyToOne()
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(name = "count")
    private Long count;

    @ManyToOne()
    @JoinColumn(name = "order_id", nullable = false, updatable = false)
    private Order order;

}
