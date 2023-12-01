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

import ru.namazov.mbp1.bookkeeping.model.WriteOffProduct;
import ru.namazov.mbp1.bookkeeping.repository.WriteOffProductRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class WriteOffProductPresenter {

    private final WriteOffProductRepository writeOffProductRepository;

    public List<WriteOffProduct> findAll() {
        return writeOffProductRepository.findAll();
    }

    public WriteOffProduct save(WriteOffProduct writeOffProduct) {
        return writeOffProductRepository.save(writeOffProduct);
    }

    public WriteOffProduct findById(Long id) {
        return writeOffProductRepository.findById(id).orElseThrow();
    }

    public List<WriteOffProduct> save(List<WriteOffProduct> writeOffProductList) {
        return writeOffProductRepository.saveAll(writeOffProductList);
    }
}
