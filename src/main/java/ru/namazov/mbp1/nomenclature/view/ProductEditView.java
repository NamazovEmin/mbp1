/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.nomenclature.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

import ru.namazov.mbp1.ViewConstructor;
import ru.namazov.mbp1.nomenclature.model.Product;
import ru.namazov.mbp1.nomenclature.presenter.ProductPresenter;
import ru.namazov.mbp1.view.BaseView;

@Route(value = "/admin/nomenclature/products/edit", layout = BaseView.class)
public class ProductEditView extends VerticalLayout implements HasUrlParameter<Long>, ViewConstructor {

    private Product product;
    private ProductPresenter productPresenter;
    private TextField nameField;
    private TextField measurementField;
    private TextField descriptionField;

    public ProductEditView(ProductPresenter productPresenter) {
        this.productPresenter = productPresenter;
    }

    @Override
    public void setParameter(BeforeEvent event,Long id) {
        if (id != null) {
            product = productPresenter.findById(id);
            header();
            main();
            footer();
        }
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
        nameField.setValue(product.getName());

        measurementField = new TextField();
        measurementField.setLabel("Единица измерения");
        measurementField.setSizeFull();
        measurementField.setValue(product.getMeasurement());

        descriptionField = new TextField();
        descriptionField.setLabel("Описание товара");
        descriptionField.setSizeFull();
        descriptionField.setValue(product.getDescription());

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
            product.setName(nameField.getValue());
            product.setMeasurement(measurementField.getValue());
            product.setDescription(descriptionField.getValue());
            productPresenter.save(product);
            UI.getCurrent().navigate(ProductView.class);
        });
        return saveButton;
    }
}
