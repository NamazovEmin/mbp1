/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.storage.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import org.springframework.security.core.context.SecurityContextHolder;

import ru.namazov.mbp1.base.ViewConstructor;
import ru.namazov.mbp1.base.view.MainView;
import ru.namazov.mbp1.storage.model.Storage;
import ru.namazov.mbp1.storage.presenter.StoragePresenter;

import jakarta.annotation.security.PermitAll;

@Route(value = "storage", layout = MainView.class)
@PermitAll
public class StorageViewAll extends VerticalLayout implements ViewConstructor {

    private final StoragePresenter storagePresenter;
    private final Grid<Storage> table = new Grid<>(Storage.class, false);


    public StorageViewAll(StoragePresenter storagePresenter) {
        this.storagePresenter = storagePresenter;
        header();
        main();
        footer();
    }

    @Override
    public void header() {

    }

    @Override
    public void main() {
        table.addColumn(Storage::getName).setHeader("Название").setResizable(true);
        table.setItems(storagePresenter.findAll());
        add(table);
    }

    @Override
    public void footer() {
       if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().filter(i -> i.getAuthority().equals("ROLE_ADMIN")).count() == 1) {
           add(new HorizontalLayout(addButton(), editButton(), readButton()));
       } else {
           add(new HorizontalLayout(readButton()));
       }
    }

    private Button addButton() {
        var createButton = new Button("Добавить");
        createButton.addSingleClickListener(click -> UI.getCurrent().navigate(StorageViewAdd.class));
        return createButton;
    }

    private Button editButton() {
        var editButton = new Button("Редактировать");
        editButton.addSingleClickListener(click -> {
            Storage storage = table.asSingleSelect().getValue();
            if (storage != null) {
                UI.getCurrent().navigate(StorageViewEdit.class, storage.getId());
            }
        });
        return editButton;
    }

    private Button readButton() {
        var readButton = new Button("Просмотреть");
        readButton.addSingleClickListener(click -> {
            Storage storage = table.asSingleSelect().getValue();
            if (storage != null) {
                UI.getCurrent().navigate(StorageProductViewAll.class, storage.getId());
            }
        });
        return readButton;
    }
}
