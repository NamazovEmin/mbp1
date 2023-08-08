/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.storage.model;

import java.util.List;

import ru.namazov.mbp1.base.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "storages")
@NoArgsConstructor
@Setter
@Getter
public class Storage extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "storage", fetch = FetchType.EAGER)
    private List<StorageProduct> storageProductList;

}
