/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.nomenclature.view.product;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import ru.namazov.mbp1.base.ViewConstructor;
import ru.namazov.mbp1.base.view.MainView;
import ru.namazov.mbp1.nomenclature.model.Product;
import ru.namazov.mbp1.nomenclature.presenter.ProductPresenter;

import jakarta.annotation.security.RolesAllowed;

@Route(value = "/admin/nomenclature/products/add", layout = MainView.class)
@RolesAllowed("ADMIN")
public class ProductAddView extends VerticalLayout implements ViewConstructor {

    private final ProductPresenter productPresenter;
    private TextField nameField;
    private TextField measurementField;
    private TextField descriptionField;

    public ProductAddView(ProductPresenter productPresenter) {
        this.productPresenter = productPresenter;
        header();
        main();
        footer();
    }

    @Override
    public void header() {
        add(new HorizontalLayout(backButton()));
    }

    @Override
    public void main() {
        nameField = new TextField();
        nameField.setLabel("Название");
        nameField.setSizeFull();

        measurementField = new TextField();
        measurementField.setLabel("Единица измерения");
        measurementField.setSizeFull();

        descriptionField = new TextField();
        descriptionField.setLabel("Описание товара");
        descriptionField.setSizeFull();

        add(nameField);
        add(measurementField);
        add(descriptionField);
    }

    @Override
    public void footer() {
        add(new HorizontalLayout(saveButton()));
    }

    private Button backButton() {
        Button backButton = new Button("Назад");
        backButton.addSingleClickListener(click -> {
            UI.getCurrent().navigate(ProductView.class);
        });
        return backButton;
    }

    private Button saveButton() {
        Button saveButton = new Button("Сохранить");
        saveButton.addSingleClickListener(click -> {
            Product product = new Product();
            product.setName(nameField.getValue());
            product.setMeasurement(measurementField.getValue());
            product.setDescription(descriptionField.getValue());
            productPresenter.save(product);
            UI.getCurrent().navigate(ProductView.class);
        });
        return saveButton;
    }
}
