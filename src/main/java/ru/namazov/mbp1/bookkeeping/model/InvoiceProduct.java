/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.bookkeeping.model;

import ru.namazov.mbp1.base.model.BaseEntity;
import ru.namazov.mbp1.nomenclature.model.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "invoice_product")
@NoArgsConstructor
@Getter
@Setter
public class InvoiceProduct extends BaseEntity {


    @Column(name = "count")
    private long count;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
}