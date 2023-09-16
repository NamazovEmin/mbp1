/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.nomenclature.model;

import ru.namazov.mbp1.base.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@NoArgsConstructor
@Setter
@Getter
public class Product extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "measurement")
    private String measurement;

    @Column(name = "description")
    private String description;
}
