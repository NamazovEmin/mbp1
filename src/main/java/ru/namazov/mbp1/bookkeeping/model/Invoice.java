/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.bookkeeping.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.namazov.mbp1.base.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "invoices")
@NoArgsConstructor
@Getter
@Setter
public class Invoice extends BaseEntity {

    @NotNull(message = "")
    @Column(name = "number")
    private long number;

    @NotNull(message = "")
    @Column(name = "date")
    private Date date;

    @NotNull(message = "")
    @Column(name = "isinstock")
    private boolean isInStock;

    @NotNull(message = "")
    @Column(name = "ispaid")
    private boolean isPaid;

    @NotNull(message = "")
    @Column(name = "isreceived")
    private boolean isReceived;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "invoice")
    private List<InvoiceProduct> invoiceProductList = new ArrayList<>();
}
