/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.bookkeeping.model;

import ru.namazov.mbp1.base.model.BaseProductTypeEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "writeoff_product")
@NoArgsConstructor
@Getter
@Setter
public class WriteOffProduct extends BaseProductTypeEntity {

    @ManyToOne
    @JoinColumn(name = "writeoff_id")
    private WriteOff writeOff;
}