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

import ru.namazov.mbp1.bookkeeping.model.WriteOff;
import ru.namazov.mbp1.bookkeeping.repository.WriteOffRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class WriteOffPresenter {

    private final WriteOffRepository writeOffRepository;

    public List<WriteOff> findAll() {
        return writeOffRepository.findAll();
    }

    public WriteOff save(WriteOff writeOff) {
        return writeOffRepository.save(writeOff);
    }

    public WriteOff findById(Long id) {
        return writeOffRepository.findById(id).orElseThrow();
    }
}
