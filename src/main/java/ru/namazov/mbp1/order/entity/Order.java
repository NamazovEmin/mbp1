/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.order.entity;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import ru.namazov.mbp1.base.model.BaseEntity;
import ru.namazov.mbp1.client.model.Client;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
@Setter
public class Order extends BaseEntity {

    @NotNull(message = "")
    @Column(name = "date")
    private Date date;

    @NotNull(message = "")
    @Column(name = "time_from")
    private LocalTime timeFrom;

    @NotNull(message = "")
    @Column(name = "time_to")
    private LocalTime timeTo;

    @NotEmpty(message = "")
    @NotNull(message = "")
    @Column(name = "address")
    private String address;

    @NotEmpty(message = "Введите имя получателя")
    @NotNull(message = "Введите имя получателя")
    @Column(name = "contact")
    private String contact;

    @NotEmpty(message = "")
    @NotNull(message = "")
    @Column(name = "telnumber")
    private String telNumber;

    @NotNull(message = "")
    @ManyToOne()
    @JoinColumn(name = "client_id", nullable = false, updatable = false)
    private Client client;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderProduct> productList;
}
