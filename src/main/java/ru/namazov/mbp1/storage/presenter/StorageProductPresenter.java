/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.storage.presenter;

import org.springframework.stereotype.Component;

import ru.namazov.mbp1.repository.StorageProductRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class StorageProductPresenter {

    private final StorageProductRepository storageProductRepository;

}
