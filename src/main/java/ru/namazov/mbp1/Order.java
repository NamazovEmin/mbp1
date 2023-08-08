/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1;

import java.util.Date;
import java.util.List;

import ru.namazov.mbp1.base.model.BaseEntity;
import ru.namazov.mbp1.client.model.Client;
import ru.namazov.mbp1.nomenclature.model.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
@Setter
public class Order extends BaseEntity {

    @Column(name = "date")
    private Date date;

    @Column(name = "time_from")
    private Long timeFrom;

    @Column(name = "time_to")
    private Long timeTo;

    @Column(name = "address")
    private String address;

    @Column(name = "contact")
    private String contact;

    @Column(name = "telnumber")
    private String telNumber;

    @ManyToOne()
    @JoinColumn(name = "client_id", nullable = false, updatable = false)
    private Client client;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "orders_products",
            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")})
    private List<Product> products;
}
