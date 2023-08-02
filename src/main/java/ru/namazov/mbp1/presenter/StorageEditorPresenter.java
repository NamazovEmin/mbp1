package ru.namazov.mbp1.presenter;

import org.springframework.stereotype.Component;

import ru.namazov.mbp1.model.Product;
import ru.namazov.mbp1.repository.ProductRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class StorageEditorPresenter {

    private final ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }
}
