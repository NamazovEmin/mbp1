/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.bookkeeping.model;

import java.util.ArrayList;
import java.util.List;

import ru.namazov.mbp1.base.model.BaseDocumentEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "writeoff")
@NoArgsConstructor
@Getter
@Setter
public class WriteOff extends BaseDocumentEntity {

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "writeOff")
    private List<WriteOffProduct> writeOffProducts = new ArrayList<>();
}
