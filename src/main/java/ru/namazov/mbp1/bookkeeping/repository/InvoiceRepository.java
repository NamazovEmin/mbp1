/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.bookkeeping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.namazov.mbp1.bookkeeping.model.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
