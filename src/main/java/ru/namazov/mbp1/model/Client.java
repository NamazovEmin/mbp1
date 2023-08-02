/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Entity
@Table(name = "clients")
@Getter
public class Client extends BaseEntity{

//    @Id
//    @Column(name = "id", nullable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "telNumber")
    private String telNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "contactMen")
    private String contactMen;

    @Override
    public String toString() {
        return "Client{" + "id=" + super.getId() + ", name='" + name + '\'' + ", telNumber='" + telNumber + '\'' + ", email='" +
                email + '\'' + ", contactMen='" + contactMen + '\'' + '}';
    }
}
