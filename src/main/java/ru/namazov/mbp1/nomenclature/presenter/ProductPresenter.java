/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.nomenclature.presenter;

import java.util.List;

import org.springframework.stereotype.Component;

import ru.namazov.mbp1.nomenclature.model.Product;
import ru.namazov.mbp1.nomenclature.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ProductPresenter {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }
}
