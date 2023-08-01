package ru.namazov.mbp1.view;



import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;

import ru.namazov.mbp1.model.Client;
import ru.namazov.mbp1.presenter.ClientEditorPresenter;

@Route(value = "clients/edit", layout = BaseView.class)
public class ClientEditView extends VerticalLayout implements HasUrlParameter<Long> {

    private Client client;
    private ClientEditorPresenter clientEditorPresenter;
    private final Grid<Client> table = new Grid<>(Client.class);

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter Long parameter) {
        if (parameter != null) {
            client = clientEditorPresenter.findById(parameter);
            createTableEdit();
        } else {
            createTableCreate();
        }
    }

    public ClientEditView(ClientEditorPresenter clientEditorPresenter) {
        this.clientEditorPresenter = clientEditorPresenter;
    }
    private void createTableCreate() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        add(horizontalLayout);

        var nameField = new TextField();
        nameField.setLabel("Название Фирмы");
        nameField.setSizeFull();

        var telNumberField = new TextField();
        telNumberField.setLabel("Телефон");
        telNumberField.setSizeFull();

        var contactMenField = new TextField();
        contactMenField.setLabel("Контактное лицо");
        contactMenField.setSizeFull();

        var emailField = new TextField();
        emailField.setLabel("Почта");
        emailField.setSizeFull();

        add(nameField);
        add(telNumberField);
        add(contactMenField);
        add(emailField);

        Button backButton = new Button("Назад");
        backButton.addSingleClickListener(click -> {
            UI.getCurrent().navigate(ClientsView.class);
        });

        Button saveButton = new Button("Сохранить");
        saveButton.addSingleClickListener(click -> {
            client = new Client();
            client.setName(nameField.getValue());
            client.setTelNumber(telNumberField.getValue());
            client.setContactMen(contactMenField.getValue());
            client.setEmail(emailField.getValue());
            clientEditorPresenter.save(client);
            nameField.setReadOnly(true);
            telNumberField.setReadOnly(true);
            contactMenField.setReadOnly(true);
            emailField.setReadOnly(true);
        });

        horizontalLayout.add(backButton, saveButton);
    }

    private void createTableEdit() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        add(horizontalLayout);

        var idField = new TextField();
        idField.setValue(String.valueOf(client.getId()));
        idField.setReadOnly(true);
        idField.setLabel("ID");
        idField.setSizeFull();

        var nameField = new TextField();
        nameField.setValue(client.getName());
        nameField.setReadOnly(true);
        nameField.setLabel("Название Фирмы");
        nameField.setSizeFull();

        var telNumberField = new TextField();
        telNumberField.setValue(client.getTelNumber());
        telNumberField.setReadOnly(true);
        telNumberField.setLabel("Телефон");
        telNumberField.setSizeFull();

        var contactMenField = new TextField();
        contactMenField.setValue(client.getContactMen());
        contactMenField.setReadOnly(true);
        contactMenField.setLabel("Контактное лицо");
        contactMenField.setSizeFull();

        var emailField = new TextField();
        emailField.setValue(client.getEmail());
        emailField.setReadOnly(true);
        emailField.setLabel("Почта");
        emailField.setSizeFull();

        add(idField);
        add(nameField);
        add(telNumberField);
        add(contactMenField);
        add(emailField);

        Button backButton = new Button("Назад");
        backButton.addSingleClickListener(click -> {
            UI.getCurrent().navigate(ClientsView.class);
        });

        Button editButton = new Button("Редактировать");
        editButton.addSingleClickListener(click -> {
            nameField.setReadOnly(false);
            telNumberField.setReadOnly(false);
            contactMenField.setReadOnly(false);
            emailField.setReadOnly(false);

        });

        Button saveButton = new Button("Сохранить");
        saveButton.addSingleClickListener(click -> {
           client.setName(nameField.getValue());
           client.setTelNumber(telNumberField.getValue());
           client.setContactMen(contactMenField.getValue());
           client.setEmail(emailField.getValue());
           clientEditorPresenter.save(client);
            nameField.setReadOnly(true);
            telNumberField.setReadOnly(true);
            contactMenField.setReadOnly(true);
            emailField.setReadOnly(true);
        });

        horizontalLayout.add(backButton, editButton, saveButton);
    }
}
