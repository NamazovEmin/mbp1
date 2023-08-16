/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.order.view;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

import ru.namazov.mbp1.base.ViewConstructor;
import ru.namazov.mbp1.base.view.MainView;
import ru.namazov.mbp1.client.model.Client;
import ru.namazov.mbp1.client.presenter.ClientPresenter;
import ru.namazov.mbp1.order.entity.Order;
import ru.namazov.mbp1.order.presenter.OrderPresenter;
import ru.namazov.mbp1.validation.Validation;

import jakarta.annotation.security.PermitAll;


@Route(value = "order/add", layout = MainView.class)
@PermitAll
public class OrderViewAdd extends VerticalLayout implements ViewConstructor {

    private final Validation validation;
    private final OrderPresenter orderPresenter;
    private final ClientPresenter clientPresenter;
    private DatePicker date;
    private ComboBox<Client> client;
    private TimePicker timeFrom;
    private TimePicker timeTo;
    private TextField address;
    private TextField contact;
    private TextField telNumber;
    private List<? extends Component> components;
    Binder<Order> binder = new BeanValidationBinder<>(Order.class);

    public OrderViewAdd(Validation validation, OrderPresenter orderPresenter, ClientPresenter clientPresenter) {
        this.validation = validation;
        this.orderPresenter = orderPresenter;
        this.clientPresenter = clientPresenter;
        header();
        main();
        footer();
    }

    @Override
    public void header() {
        add(new HorizontalLayout(backButton(), saveButton()));
    }

    @Override
    public void main() {
        client = new ComboBox<>("Название Фирмы");
        client.setItems(clientPresenter.findAll());
        client.setItemLabelGenerator(Client::getName);
        client.setErrorMessage("Выберите название фирмы из списка");

        date = new DatePicker();
        date.setLabel("День доставки");
        date.setValue(LocalDate.now().plusDays(1));
        date.setErrorMessage("Выберите дату доставки");

        timeFrom = new TimePicker();
        timeFrom.setLabel("C ");
        timeFrom.setStep(Duration.ofMinutes(30));
        timeFrom.setValue(LocalTime.of(10, 0));
        timeFrom.setErrorMessage("Выберите интервал доставки");

        timeTo = new TimePicker();
        timeTo.setLabel("По ");
        timeTo.setStep(Duration.ofMinutes(30));
        timeTo.setValue(LocalTime.of(17, 0));
        timeTo.setErrorMessage("Выберите интервал доставки");

        address = new TextField();
        address.setLabel("Адрес");
        address.setSizeFull();
        address.setErrorMessage("Введите Адрес доставки");

        contact = new TextField();
        contact.setLabel("Контактное лицо");
        contact.setSizeFull();
        contact.setErrorMessage("Введите имя Получателя");

        telNumber = new TextField();
        telNumber.setLabel("Телефон");
        telNumber.setSizeFull();
        telNumber.setErrorMessage("Введите телефон получателя");

        add(client);
        add(date);
        add(timeFrom);
        add(timeTo);
        add(address);
        add(contact);
        add(telNumber);
        binder.bindInstanceFields(this);
        components = List.of(
                client,
                date,
                timeFrom,
                timeTo,
                address,
                contact,
                telNumber);
    }

    @Override
    public void footer() {
        add(new HorizontalLayout(backButton(), saveButton()));
    }

    private Button saveButton() {
        Button saveButton = new Button("Сохранить");
        saveButton.addSingleClickListener(click -> {
            if (validation.isValid(components)) {
                Order order = new Order();
                order.setDate(Date.from(Instant.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
                order.setClient(client.getValue());
                order.setTimeFrom(timeFrom.getValue());
                order.setTimeTo(timeTo.getValue());
                order.setAddress(address.getValue());
                order.setContact(contact.getValue());
                order.setTelNumber(telNumber.getValue());
                orderPresenter.save(order);
                UI.getCurrent().navigate(OrderViewAll.class);
            }
        });
        return saveButton;
    }

    private Button backButton() {
        Button backButton = new Button("Назад");
        backButton.addSingleClickListener(click -> UI.getCurrent().navigate(OrderViewAll.class));
        return backButton;
    }
}
