/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.order.presenter;

import java.util.List;

import org.springframework.stereotype.Component;

import ru.namazov.mbp1.order.entity.Order;
import ru.namazov.mbp1.order.repository.OrderRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class OrderPresenter {

    private final OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }
}
