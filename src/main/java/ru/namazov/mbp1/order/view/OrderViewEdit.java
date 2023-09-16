/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.order.view;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
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
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

import ru.namazov.mbp1.base.ViewConstructor;
import ru.namazov.mbp1.base.view.MainView;
import ru.namazov.mbp1.client.model.Client;
import ru.namazov.mbp1.client.presenter.ClientPresenter;
import ru.namazov.mbp1.nomenclature.model.Product;
import ru.namazov.mbp1.nomenclature.presenter.ProductPresenter;
import ru.namazov.mbp1.order.entity.Order;
import ru.namazov.mbp1.order.presenter.OrderPresenter;

import jakarta.annotation.security.PermitAll;

@Route(value = "order/edit", layout = MainView.class)
@PermitAll
public class OrderViewEdit extends VerticalLayout implements HasUrlParameter<Long>, ViewConstructor {

    private Order order;
    private final OrderPresenter orderPresenter;
    private final ClientPresenter clientPresenter;
    private final ProductPresenter productPresenter;
    private DatePicker date;
    private ComboBox<Client> client;
    private TimePicker timeFrom;
    private TimePicker timeTo;
    private TextField address;
    private TextField contact;
    private TextField telNumber;
    private Binder<Order> binder = new BeanValidationBinder<>(Order.class);
    private List<? extends Component> components;

    public OrderViewEdit(OrderPresenter orderPresenter, ClientPresenter clientPresenter,
            ProductPresenter productPresenter) {
        this.orderPresenter = orderPresenter;
        this.clientPresenter = clientPresenter;
        this.productPresenter = productPresenter;
    }

    @Override
    public void setParameter(BeforeEvent event, Long id) {
        if (id != null) {
            order = orderPresenter.findById(id);
            header();
            main();
            footer();
        }
    }

    @Override
    public void header() {
        add(new HorizontalLayout(backButton(), saveButton()));
    }

    @Override
    public void main() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        add(horizontalLayout);

        client = new ComboBox<>("Название Фирмы");
        client.setItems(clientPresenter.findAll());
        client.setItemLabelGenerator(Client::getName);
        client.setErrorMessage("Выберите название фирмы из списка");
        client.setSizeFull();
        client.setValue(order.getClient());
        client.setReadOnly(true);

        date = new DatePicker("День доставки");
        date.setErrorMessage("Выберите дату доставки");
        client.setSizeFull();
        date.setValue(LocalDate.ofInstant(order.getDate().toInstant(), ZoneId.systemDefault()));

        timeFrom = new TimePicker("C ");
        timeFrom.setStep(Duration.ofMinutes(30));
        timeFrom.setErrorMessage("Выберите интервал доставки");
        timeFrom.setSizeFull();
        timeFrom.setValue(order.getTimeFrom());

        timeTo = new TimePicker("По ");
        timeTo.setStep(Duration.ofMinutes(30));
        timeTo.setErrorMessage("Выберите интервал доставки");
        timeTo.setSizeFull();
        timeTo.setValue(order.getTimeTo());

        address = new TextField("Адрес");
        address.setErrorMessage("Введите Адрес доставки");
        address.setSizeFull();
        address.setValue(order.getAddress());

        contact = new TextField("Контактное лицо");
        contact.setSizeFull();
        contact.setErrorMessage("Введите имя Получателя");
        contact.setValue(order.getContact());


        telNumber = new TextField("Телефон");
        telNumber.setErrorMessage("Введите телефон получателя");
        telNumber.setSizeFull();
        telNumber.setValue(order.getTelNumber());

        add(client);
        add(date);
        add(timeFrom);
        add(timeTo);
        add(address);
        add(contact);
        add(telNumber);
        add(makeProductMenu());
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

    private VerticalLayout makeProductMenu() {
        VerticalLayout productMenu = new VerticalLayout();
        order.getProductList().forEach(prod -> {
            ComboBox<Product> product = new ComboBox<>("Название Продукта");
            product.setItems(productPresenter.findAll());
            product.setItemLabelGenerator(Product::getName);
            product.setValue(prod.getProduct());
            product.setErrorMessage("Выберите название продукта из списка");

            TextField count = new TextField("шт");
            count.setValue(prod.getCount().toString());
            count.setErrorMessage("Введите кол-во товара");

            productMenu.add(new HorizontalLayout(product, count));
        });
        return productMenu;
    }

    private Button saveButton() {
        Button saveButton = new Button("Сохранить");
        saveButton.addSingleClickListener(click -> {
            order.setDate(Date.from(Instant.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
            order.setClient(client.getValue());
            order.setTimeFrom(timeFrom.getValue());
            order.setTimeTo(timeTo.getValue());
            order.setAddress(address.getValue());
            order.setContact(contact.getValue());
            order.setTelNumber(telNumber.getValue());
            orderPresenter.save(order);
            UI.getCurrent().navigate(OrderViewAll.class);
        });

        return saveButton;
    }

    private Button backButton() {
        Button backButton = new Button("Назад");
        backButton.addSingleClickListener(click -> {
            UI.getCurrent().navigate(OrderViewAll.class);
        });
        return backButton;
    }
}
