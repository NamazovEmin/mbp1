/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.fomatcheck;

import com.vaadin.flow.component.textfield.TextField;

public class FieldFormatCheckImpl implements FieldFormatCheck {

    @Override
    public boolean isNumberFormatTextField(TextField textField) {
        try {
            Long.parseLong(textField.getValue());
            return true;
        } catch (NumberFormatException ignored) {
            return false;
        }
    }
}
