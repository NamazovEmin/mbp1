/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.editor;

import java.util.List;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import ru.namazov.mbp1.model.Client;

public interface ClientViewEditor {

    List<HorizontalLayout> prepareRowsFromDBToTable(List<Client> clients);
}
