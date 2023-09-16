/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.order.presenter;

import org.springframework.stereotype.Component;

import ru.namazov.mbp1.order.entity.OrderProduct;
import ru.namazov.mbp1.order.repository.OrderProductRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class OrderProductPresenter {

    private final OrderProductRepository orderProductRepository;

    public OrderProduct save(OrderProduct orderProduct) {
        return orderProductRepository.save(orderProduct);
    }
}
