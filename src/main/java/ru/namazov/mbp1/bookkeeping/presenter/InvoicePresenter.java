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

import ru.namazov.mbp1.bookkeeping.model.Invoice;
import ru.namazov.mbp1.bookkeeping.repository.InvoiceRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class InvoicePresenter {

    private final InvoiceRepository invoiceRepository;

    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    public Invoice save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public Invoice findById(Long id) {
        return invoiceRepository.findById(id).orElseThrow();
    }

    public Invoice findByNumber(long number) {
        return invoiceRepository.findByNumber(number).orElseThrow();
    }
}
