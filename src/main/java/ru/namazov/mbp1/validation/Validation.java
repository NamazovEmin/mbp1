/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.validation;

import java.util.List;

import com.vaadin.flow.component.Component;

public interface Validation {

    boolean isValid(List<? extends Component> components);
    boolean isValid(Component component);
}
