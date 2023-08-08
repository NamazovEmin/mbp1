/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.storage.presenter;

import java.util.List;

import org.springframework.stereotype.Component;

import ru.namazov.mbp1.storage.model.Storage;
import ru.namazov.mbp1.storage.repository.StorageRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class StoragePresenter {

    private final StorageRepository storageRepository;

    public List<Storage> findAll() {
        return storageRepository.findAll();
    }

    public Storage save(Storage storage) {
        return storageRepository.save(storage);
    }

    public Storage findById(Long id) {
        return storageRepository.findById(id).orElseThrow();
    }
}
