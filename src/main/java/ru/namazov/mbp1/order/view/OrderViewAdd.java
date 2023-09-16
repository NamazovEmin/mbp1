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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.router.Route;

import ru.namazov.mbp1.base.ViewConstructor;
import ru.namazov.mbp1.base.view.MainView;
import ru.namazov.mbp1.client.model.Client;
import ru.namazov.mbp1.client.presenter.ClientPresenter;
import ru.namazov.mbp1.nomenclature.model.Product;
import ru.namazov.mbp1.nomenclature.presenter.ProductPresenter;
import ru.namazov.mbp1.order.entity.Order;
import ru.namazov.mbp1.order.entity.OrderProduct;
import ru.namazov.mbp1.order.presenter.OrderPresenter;
import ru.namazov.mbp1.order.presenter.OrderProductPresenter;
import ru.namazov.mbp1.validation.Validation;

import jakarta.annotation.security.PermitAll;


@Route(value = "order/add", layout = MainView.class)
@PermitAll
public class OrderViewAdd extends VerticalLayout implements ViewConstructor {

    private final Validation validation;
    private final OrderPresenter orderPresenter;
    private final ClientPresenter clientPresenter;
    private final ProductPresenter productPresenter;
    private final OrderProductPresenter orderProductPresenter;
    private VerticalLayout productMenu = new VerticalLayout();
    private DatePicker date;
    private ComboBox<Client> client;
    private ComboBox<Product> product;
    private TimePicker timeFrom;
    private TimePicker timeTo;
    private TextField address;
    private TextField contact;
    private TextField telNumber;
    private TextField count;
    private List<? extends Component> components;
    private Binder<Order> orderBinder = new BeanValidationBinder<>(Order.class);
    HorizontalLayout horizontalLayout;

    public OrderViewAdd(Validation validation, OrderPresenter orderPresenter, ClientPresenter clientPresenter,
            ProductPresenter productPresenter, OrderProductPresenter orderProductPresenter) {
        this.validation = validation;
        this.orderPresenter = orderPresenter;
        this.clientPresenter = clientPresenter;
        this.productPresenter = productPresenter;
        this.orderProductPresenter = orderProductPresenter;
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

        date = new DatePicker("День доставки");
        date.setValue(LocalDate.now().plusDays(1));
        date.setErrorMessage("Выберите дату доставки");

        timeFrom = new TimePicker("C ");
        timeFrom.setStep(Duration.ofMinutes(30));
        timeFrom.setValue(LocalTime.of(10, 0));
        timeFrom.setErrorMessage("Выберите интервал доставки");

        timeTo = new TimePicker("По ");
        timeTo.setStep(Duration.ofMinutes(30));
        timeTo.setValue(LocalTime.of(17, 0));
        timeTo.setErrorMessage("Выберите интервал доставки");

        address = new TextField("Адрес");
        address.setSizeFull();
        address.setErrorMessage("Введите Адрес доставки");

        contact = new TextField("Контактное лицо");
        contact.setSizeFull();
        contact.setErrorMessage("Введите имя Получателя");

        telNumber = new TextField("Телефон");
        telNumber.setSizeFull();
        telNumber.setErrorMessage("Введите телефон получателя");

        product = new ComboBox<>("Название Продукта");
        product.setItems(productPresenter.findAll());
        product.setItemLabelGenerator(Product::getName);
        product.setErrorMessage("Выберите название продукта из списка");

        count = new TextField("шт");
        count.setErrorMessage("Введите кол-во товара");

        Button delButton = new Button("-");
        delButton.addSingleClickListener(click -> {
            if (delButton.getParent().isPresent()) {
                delButton.getParent().get().setVisible(false);
            }
        });

        Button addButton = new Button("+");
        buttonClick(addButton);



        add(client);
        add(date);
        add(timeFrom);
        add(timeTo);
        add(address);
        add(contact);
        add(telNumber);
        add(productMenu);
        productMenu.add(new HorizontalLayout(product, count, delButton, addButton));
        orderBinder.bindInstanceFields(this);
        components = List.of(
                client,
                date,
                timeFrom,
                timeTo,
                address,
                contact,
                telNumber);
    }


    private void buttonClick(Button button) {
        button.addSingleClickListener(click -> {
            productMenu.getChildren().forEach(hor -> {
                hor.getChildren().forEach(component -> {
                    if (component instanceof  Button but && but.getText().equals("+")) {
                        but.setVisible(false);
                    }
                });
            });


            ComboBox<Product> addProduct = new ComboBox<>("Название Продукта");
            addProduct.setItems(productPresenter.findAll());
            addProduct.setItemLabelGenerator(Product::getName);
            addProduct.setErrorMessage("Выберите название продукта из списка");

            TextField addCount = new TextField("шт");
            addCount.setErrorMessage("Введите кол-во товара");

            Button delBut = new Button("-");
            delBut.addSingleClickListener(clck -> {
                if (delBut.getParent().isPresent()) {
                    delBut.getParent().get().setVisible(false);
                }
            });

            Button addBut = new Button("+");
            addBut.addSingleClickListener(lick -> buttonClick(addBut));
            productMenu.add(new HorizontalLayout(addProduct, addCount, delBut, addBut));
        });
    }

    @Override
    public void footer() {
        horizontalLayout = new HorizontalLayout(backButton(), saveButton());
        add(horizontalLayout);
    }

    private Button saveButton() {
        Button saveButton = new Button("Сохранить");
        saveButton.addSingleClickListener(click -> {
            if (orderBinder.isValid()) {
                List<OrderProduct> productList = new ArrayList<>();
                Order order = new Order();
                order.setDate(Date.from(Instant.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
                order.setClient(client.getValue());
                order.setTimeFrom(timeFrom.getValue());
                order.setTimeTo(timeTo.getValue());
                order.setAddress(address.getValue());
                order.setContact(contact.getValue());
                order.setTelNumber(telNumber.getValue());
                productMenu.getChildren().forEach(line -> {
                    OrderProduct orderProduct = new OrderProduct();
                    line.getChildren().forEach(element -> {
                        if (element instanceof ComboBox<?> prod) {
                            orderProduct.setProduct((Product) prod.getValue());
                        }
                        if (element instanceof TextField textField) {
                            orderProduct.setCount(Long.parseLong(textField.getValue()));
                        }
                        productList.add(orderProduct);
                    });
                });
                order.setProductList(productList);
                Order savedOrder = orderPresenter.save(order);
                productList.forEach(i -> {
                    i.setOrder(savedOrder);
                    orderProductPresenter.save(i);
                });
                UI.getCurrent().navigate(OrderViewAll.class);
            } else {
                BinderValidationStatus<Order> validate = orderBinder.validate();
                validate.getBeanValidationErrors().forEach(ValidationResult::getErrorMessage);
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
