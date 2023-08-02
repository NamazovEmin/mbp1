package ru.namazov.mbp1.presenter;

import java.util.List;

import org.springframework.stereotype.Component;

import ru.namazov.mbp1.model.Product;
import ru.namazov.mbp1.repository.ProductRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class StoragePresenter {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
