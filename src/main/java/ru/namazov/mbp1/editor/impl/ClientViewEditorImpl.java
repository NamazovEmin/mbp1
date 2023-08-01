/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.editor.impl;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

import ru.namazov.mbp1.editor.ClientViewEditor;
import ru.namazov.mbp1.model.Client;

//@Component
public class ClientViewEditorImpl implements ClientViewEditor {

    @Override
    public List<HorizontalLayout> prepareRowsFromDBToTable(List<Client> clients) {
        List<HorizontalLayout> rows = new ArrayList<>();
        rows.add(prepareFirstRow(clients));
        clients.remove(0);
        clients.forEach(i -> {
            HorizontalLayout horizontalLayout = new HorizontalLayout();

            TextField nameField = new TextField();
            nameField.setReadOnly(true);
            nameField.setValue(i.getName());

            TextField telNumberField = new TextField();
            telNumberField.setReadOnly(true);
            telNumberField.setValue(i.getTelNumber());

            TextField contactMenField = new TextField();
            contactMenField.setReadOnly(true);
            contactMenField.setValue(i.getContactMen());

            TextField emailField = new TextField();
            emailField.setReadOnly(true);
            emailField.setValue(i.getEmail());

            Button editButton = new Button();
            editButton.setText("Edit");
            editButton.addClickListener(click -> {
                if (click.getClickCount()%2 != 0) {
                    nameField.setReadOnly(false);
                    telNumberField.setReadOnly(false);
                    contactMenField.setReadOnly(false);
                    emailField.setReadOnly(false);
                } else {
                    nameField.setReadOnly(true);
                    telNumberField.setReadOnly(true);
                    contactMenField.setReadOnly(true);
                    emailField.setReadOnly(true);
                }
            });

            Button saveButton = new Button();
            saveButton.setText("Edit");
            saveButton.addClickListener(click -> {

            });

            horizontalLayout.add(nameField);
            horizontalLayout.add(telNumberField);
            horizontalLayout.add(contactMenField);
            horizontalLayout.add(emailField);
            horizontalLayout.add(editButton);

            horizontalLayout.setMargin(false);
            horizontalLayout.setPadding(false);
//            horizontalLayout.setSpacing(false);
            rows.add(horizontalLayout);
        });

        return rows;
    }

    private HorizontalLayout prepareFirstRow(List<Client> clients) {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setMargin(false);
        horizontalLayout.setPadding(false);
//        horizontalLayout.setSpacing(false);

        TextField nameField = new TextField();
        nameField.setReadOnly(true);
        nameField.setLabel("Название Фирмы");
        nameField.setValue(clients.get(0).getName());

        TextField telNumberField = new TextField();
        telNumberField.setReadOnly(true);
        telNumberField.setLabel("Номер телефона");
        telNumberField.setValue(clients.get(0).getTelNumber());

        TextField contactMenField = new TextField();
        contactMenField.setReadOnly(true);
        contactMenField.setLabel("Контактное лицо");
        contactMenField.setValue(clients.get(0).getContactMen());

        TextField emailField = new TextField();
        emailField.setReadOnly(true);
        emailField.setLabel("Почта");
        emailField.setValue(clients.get(0).getEmail());

        Button editButton = new Button();
        editButton.setText("Edit");
        editButton.addClickListener(click -> {
            if (click.getClickCount()%2 != 0) {
                nameField.setReadOnly(false);
                telNumberField.setReadOnly(false);
                contactMenField.setReadOnly(false);
                emailField.setReadOnly(false);
            } else {
                nameField.setReadOnly(true);
                telNumberField.setReadOnly(true);
                contactMenField.setReadOnly(true);
                emailField.setReadOnly(true);
            }
        });

        horizontalLayout.add(nameField);
        horizontalLayout.add(telNumberField);
        horizontalLayout.add(contactMenField);
        horizontalLayout.add(emailField);
        horizontalLayout.add(editButton);
        horizontalLayout.setAlignItems(FlexComponent.Alignment.BASELINE);

        return horizontalLayout;
    }
}
