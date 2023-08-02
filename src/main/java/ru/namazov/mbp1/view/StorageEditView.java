package ru.namazov.mbp1.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;

import ru.namazov.mbp1.model.Product;
import ru.namazov.mbp1.presenter.StorageEditorPresenter;

@Route(value = "storage/edit", layout = BaseView.class)
public class StorageEditView extends VerticalLayout implements HasUrlParameter<Long> {

    private final StorageEditorPresenter storageEditorPresenter;
    private Product product;

    public StorageEditView(StorageEditorPresenter storageEditorPresenter) {
        this.storageEditorPresenter = storageEditorPresenter;
    }
    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter Long id) {
        if (id != null) {
            product = storageEditorPresenter.findById(id);
            createTableEdit();
        } else {
            createTableCreate();
        }
    }

    private void createTableCreate() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        add(horizontalLayout);

        var nameField = new TextField();
        nameField.setLabel("Название");
        nameField.setSizeFull();

        var measurementField = new TextField();
        measurementField.setLabel("Единица измерения");
        measurementField.setSizeFull();

        var descriptionField = new TextField();
        descriptionField.setLabel("Описание");
        descriptionField.setSizeFull();

        add(nameField);
        add(measurementField);
        add(descriptionField);

        Button backButton = new Button("Назад");
        backButton.addSingleClickListener(click -> {
            UI.getCurrent().navigate(StorageView.class);
        });

        Button saveButton = new Button("Сохранить");
        saveButton.addSingleClickListener(click -> {
            Product product = new Product();
            product.setName(nameField.getValue());
            product.setCount(0L);
            product.setMeasurement(measurementField.getValue());
            product.setDescription(descriptionField.getValue());
            storageEditorPresenter.save(product);
            nameField.setReadOnly(true);
            measurementField.setReadOnly(true);
            descriptionField.setReadOnly(true);
        });

        horizontalLayout.add(backButton, saveButton);
    }

    private void createTableEdit() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        add(horizontalLayout);

        var idField = new TextField();
        idField.setValue(String.valueOf(product.getId()));
        idField.setReadOnly(true);
        idField.setLabel("ID");
        idField.setSizeFull();

        var nameField = new TextField();
        nameField.setValue(product.getName());
        nameField.setReadOnly(true);
        nameField.setLabel("Название");
        nameField.setSizeFull();

        var countField = new TextField();
        countField.setValue(String.valueOf(product.getCount()));
        countField.setReadOnly(true);
        countField.setLabel("Количество");
        countField.setSizeFull();

        var measurementField = new TextField();
        measurementField.setValue(product.getMeasurement());
        measurementField.setReadOnly(true);
        measurementField.setLabel("Единица измерения");
        measurementField.setSizeFull();

        var descriptionField = new TextField();
        descriptionField.setValue(product.getDescription());
        descriptionField.setReadOnly(true);
        descriptionField.setLabel("Описание");
        descriptionField.setSizeFull();

        add(idField);
        add(nameField);
        add(countField);
        add(measurementField);
        add(descriptionField);

        Button backButton = new Button("Назад");
        backButton.addSingleClickListener(click -> UI.getCurrent().navigate(StorageView.class));

        Button editButton = new Button("Редактировать");
        editButton.addSingleClickListener(click -> {
            nameField.setReadOnly(false);
            measurementField.setReadOnly(false);
            descriptionField.setReadOnly(false);
        });

        Button saveButton = new Button("Сохранить");
        saveButton.addSingleClickListener(click -> {
            product.setName(nameField.getValue());
            product.setMeasurement(measurementField.getValue());
            product.setDescription(descriptionField.getValue());
            storageEditorPresenter.save(product);
            nameField.setReadOnly(true);
            measurementField.setReadOnly(true);
            descriptionField.setReadOnly(true);
        });
        horizontalLayout.add(backButton, editButton, saveButton);
    }
}
