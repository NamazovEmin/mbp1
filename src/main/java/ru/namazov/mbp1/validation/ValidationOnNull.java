/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.validation;

import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;

@org.springframework.stereotype.Component
public class ValidationOnNull implements Validation {

    @Override
    public boolean isValid(List<? extends Component> components) {
       return components.stream().filter(this::isValid).count() == components.size();
    }

    @Override
    public boolean isValid(Component component) {
        if ((component instanceof ComboBox comboBox) && (comboBox.getValue() == null)) {
            comboBox.setInvalid(true);
            return false;
        }
        if (component instanceof TextField textField && textField.getValue() == null) {
            textField.setInvalid(true);
            return false;
        }

        if (component instanceof DatePicker datePicker && datePicker.getValue() == null) {
            datePicker.setInvalid(true);
            return false;
        }

        if (component instanceof TimePicker timePicker && timePicker.getValue() == null) {
            timePicker.setInvalid(true);
            return false;
        }
        return true;
    }
}
