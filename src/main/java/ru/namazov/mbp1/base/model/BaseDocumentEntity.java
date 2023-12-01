/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.base.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Data
@Getter
@Setter
public class BaseDocumentEntity extends BaseEntity{

    @Column(name = "number")
    private long number;

    @Column(name = "date")
    private Date date;

    @Column(name = "isinstock")
    private boolean isInStock;
}
