/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.client.model;

import java.util.List;

import ru.namazov.mbp1.model.BaseEntity;
import ru.namazov.mbp1.model.Order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Entity
@Table(name = "clients")
@Getter
public class Client extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "telNumber")
    private String telNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "contactMen")
    private String contactMen;

    @OneToMany(mappedBy = "client")
    private List<Order> orders;

    @Override
    public String toString() {
        return "Client{" + "id=" + super.getId() + ", name='" + name + '\'' + ", telNumber='" + telNumber + '\'' + ", email='" +
                email + '\'' + ", contactMen='" + contactMen + '\'' + '}';
    }
}
