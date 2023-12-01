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

package ru.namazov.mbp1.bookkeeping.presenter;

import java.util.List;

import org.springframework.stereotype.Component;

import ru.namazov.mbp1.bookkeeping.model.InvoiceProduct;
import ru.namazov.mbp1.bookkeeping.repository.InvoiceProductRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class InvoiceProductPresenter {

    private final InvoiceProductRepository invoiceProductRepository;

    public List<InvoiceProduct> findAll() {
        return invoiceProductRepository.findAll();
    }

    public InvoiceProduct save(InvoiceProduct invoiceProduct) {
        return invoiceProductRepository.save(invoiceProduct);
    }
}
