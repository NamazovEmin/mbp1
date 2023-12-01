package ru.namazov.mbp1.storage.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

import org.springframework.security.core.context.SecurityContextHolder;

import ru.namazov.mbp1.base.ViewConstructor;
import ru.namazov.mbp1.base.view.MainView;
import ru.namazov.mbp1.bookkeeping.model.Invoice;
import ru.namazov.mbp1.bookkeeping.model.InvoiceProduct;
import ru.namazov.mbp1.bookkeeping.model.WriteOff;
import ru.namazov.mbp1.bookkeeping.model.WriteOffProduct;
import ru.namazov.mbp1.bookkeeping.presenter.InvoicePresenter;
import ru.namazov.mbp1.bookkeeping.presenter.WriteOffPresenter;
import ru.namazov.mbp1.bookkeeping.presenter.WriteOffProductPresenter;
import ru.namazov.mbp1.fomatcheck.FieldFormatCheckImpl;
import ru.namazov.mbp1.nomenclature.model.Product;
import ru.namazov.mbp1.nomenclature.presenter.ProductPresenter;
import ru.namazov.mbp1.storage.model.Storage;
import ru.namazov.mbp1.storage.model.StorageProduct;
import ru.namazov.mbp1.storage.presenter.StoragePresenter;
import ru.namazov.mbp1.storage.presenter.StorageProductPresenter;

import jakarta.annotation.security.PermitAll;

@Route(value = "storage", layout = MainView.class)
@PermitAll
public class StorageView extends VerticalLayout implements HasUrlParameter<Long>, ViewConstructor {

    private final FieldFormatCheckImpl formatCheck;
    private final StoragePresenter storagePresenter;
    private final InvoicePresenter invoicePresenter;
    private final StorageProductPresenter storageProductPresenter;
    private final ProductPresenter productPresenter;
    private final WriteOffProductPresenter writeOffProductPresenter;
    private final WriteOffPresenter writeOffPresenter;
    private final Grid<StorageProduct> table = new Grid<>(StorageProduct.class, false);
    private Storage storage;
    private TextField invoiceNumberField;
    private TextField invoiceDateField;
    private TextField invoiceIsinstock;
    private final Grid<InvoiceProduct> productTable = new Grid<>(InvoiceProduct.class, false);
    private final Grid<WriteOffProduct> writeOffProductTable = new Grid<>(WriteOffProduct.class, false);
    private VerticalLayout productMenu = new VerticalLayout();
    private VerticalLayout writeOffProductMenu = new VerticalLayout();
    private TextField searchInvoiceField = new TextField("Введите номер накладной");
    private Button searchButton;
    private HorizontalLayout searchInvoiceMenu;
    private Button submitButton;
    private Invoice invoice;
    private List<WriteOffProduct> writeOffProductList = new ArrayList<>();


    public StorageView(StoragePresenter storagePresenter, InvoicePresenter invoicePresenter,
            StorageProductPresenter storageProductPresenter, ProductPresenter productPresenter,
            WriteOffProductPresenter writeOffProductPresenter, WriteOffPresenter writeOffPresenter) {
        this.invoicePresenter = invoicePresenter;
        this.storageProductPresenter = storageProductPresenter;
        this.productPresenter = productPresenter;
        this.writeOffProductPresenter = writeOffProductPresenter;
        this.writeOffPresenter = writeOffPresenter;
        this.formatCheck = new FieldFormatCheckImpl();
        this.storagePresenter = storagePresenter;
    }

    @Override
    public void setParameter(BeforeEvent event, Long id) {
        if (id != null) {
            storage = storagePresenter.findById(id);
            header();
            main();
            footer();
        }
    }

    @Override
    public void header() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        TextField storageName = new TextField();
        storageName.setValue("Склад " + storage.getName());
        storageName.setReadOnly(true);
        storageName.setSizeFull();
        horizontalLayout.add(storageName);
        add(horizontalLayout);
    }

    @Override
    public void main() {
        table.addColumn(StorageProduct::getProductName).setHeader("Название").setResizable(true);
        table.addColumn(StorageProduct::getCount).setHeader("Количество").setResizable(true);
        table.addColumn(StorageProduct::getProductMeasurement).setHeader("Единица измерения").setResizable(true);
        table.addColumn(StorageProduct::getProductDescription).setHeader("Описание").setResizable(true);
        table.setItems(storage.getStorageProductList());
        add(table);
    }

    @Override
    public void footer() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        Button editButton = new Button("Списать");
        editButton.addSingleClickListener(click -> {
            if(!writeOffProductMenu.isVisible()) {
                writeOffProductMenu.setVisible(true);
            }
        });

        Button addButton = new Button("Приход по накладной");
        addButton.addSingleClickListener(click -> {
            if (!searchInvoiceMenu.isVisible()) {
                searchInvoiceMenu.setVisible(true);
            }
        });

        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().filter(i -> i.getAuthority().equals("ROLE_ADMIN")).count() == 1) {
            horizontalLayout.add(editButton);
        }

        horizontalLayout.add(addButton);
        add(horizontalLayout);
        invoiceSearchMenu();
        writeOffMenu();
    }

    private void writeOffMenu() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        ComboBox<Product> productComboBox = new ComboBox<>("Название Продукта");
        productComboBox.setItems(productPresenter.findAll());
        TextField countField = new TextField("Количество");
        Button addToWriteOffButton = new Button("Добавить в список");
        addToWriteOffButton.addSingleClickListener(click -> {
           if (productComboBox.getValue() != null && countField.getValue() != null && formatCheck.isNumberFormatTextField(countField)) {
               WriteOffProduct writeOffProduct = new WriteOffProduct();
               writeOffProduct.setProduct(productComboBox.getValue());
               writeOffProduct.setCount(Long.parseLong(countField.getValue()));
               writeOffProductList.add(writeOffProduct);
               writeOffProductTable.setItems(writeOffProductList);
           }
        });

        horizontalLayout.add(productComboBox, countField, addToWriteOffButton);

        writeOffProductTable.addColumn(WriteOffProduct::getProductName).setHeader("Название").setResizable(true);
        writeOffProductTable.addColumn(WriteOffProduct::getCount).setHeader("Количество").setResizable(true);

        Button submitWriteOffButton = new Button("Списать данный список товаров");
        submitWriteOffButton.addSingleClickListener(click -> {
            WriteOff writeOff = new WriteOff();
            WriteOff savedWriteOff = writeOffPresenter.save(writeOff);
            writeOffProductList.forEach(writeOffProduct -> {
                writeOffProduct.setWriteOff(savedWriteOff);
            });
            writeOffProductPresenter.save(writeOffProductList);
            writeOff.setDate(new Date(System.currentTimeMillis()));
            writeOff.setNumber(writeOff.getId());
            writeOff.setInStock(false);
            writeOffPresenter.save(writeOff);
        });


        writeOffProductMenu.add(horizontalLayout, writeOffProductTable, submitWriteOffButton);
        add(writeOffProductMenu);
        writeOffProductMenu.setVisible(false);
    }

    private void invoiceSearchMenu() {
        searchInvoiceMenu = new HorizontalLayout();

        searchButton = new Button("Поиск");
        searchButton.addSingleClickListener(cl -> {
            if (formatCheck.isNumberFormatTextField(searchInvoiceField)) {
                invoice = invoicePresenter.findByNumber(Long.parseLong(searchInvoiceField.getValue()));
                productMenu.setVisible(true);
                invoiceNumberField.setValue(String.valueOf(invoice.getNumber()));
                invoiceDateField.setValue(String.valueOf(invoice.getDate()));
                invoiceIsinstock.setValue(String.valueOf(invoice.isInStock()));
                productTable.setItems(invoice.getInvoiceProductList());
            } else {
                searchInvoiceField.setErrorMessage("Неверный номер накладной");
            }
        });

        searchInvoiceMenu.add(searchInvoiceField, searchButton);
        searchInvoiceMenu.setVisible(false);
        add(searchInvoiceMenu);

        invoiceNumberField = new TextField("Номер накладной");
        invoiceNumberField.setReadOnly(true);

        invoiceDateField = new TextField("Дата");
        invoiceDateField.setReadOnly(true);

        invoiceIsinstock = new TextField("Накладная получена?");
        invoiceIsinstock.setReadOnly(true);

        productTable.addColumn(InvoiceProduct::getProduct).setHeader("Название").setResizable(true);
        productTable.addColumn(InvoiceProduct::getCount).setHeader("Количество").setResizable(true);

        submitButton = new Button("Принять товары на склад по накладной");
        submitButton.addSingleClickListener(click -> {

            invoice.getInvoiceProductList().forEach(invoiceProd -> {
                StorageProduct storageProduct = storage.getStorageProductList().stream().filter(storageProd -> {
                    if (storageProd.getProduct().getName().equals(invoiceProd.getProduct().getName())) {
                        storageProd.setCount(storageProd.getCount() + invoiceProd.getCount());
                        return true;
                    }
                    return false;
                }).findFirst().orElse(new StorageProduct(invoiceProd.getCount(), invoiceProd.getProduct(), storage));
                storageProductPresenter.save(storageProduct);
            });
            UI.getCurrent().getPage().reload();
        });

        productMenu.add(invoiceNumberField, invoiceDateField, invoiceIsinstock, productTable, submitButton);
        productMenu.setVisible(false);
        add(productMenu);
    }

}
