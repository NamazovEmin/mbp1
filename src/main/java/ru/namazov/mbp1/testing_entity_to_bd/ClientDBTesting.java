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

package ru.namazov.mbp1.testing_entity_to_bd;

import java.time.LocalTime;
import java.util.Date;

import org.springframework.stereotype.Component;

import ru.namazov.mbp1.bookkeeping.model.Invoice;
import ru.namazov.mbp1.bookkeeping.model.InvoiceProduct;
import ru.namazov.mbp1.bookkeeping.repository.InvoiceProductRepository;
import ru.namazov.mbp1.bookkeeping.repository.InvoiceRepository;
import ru.namazov.mbp1.client.model.Client;
import ru.namazov.mbp1.client.reoisitory.ClientRepository;
import ru.namazov.mbp1.nomenclature.model.Product;
import ru.namazov.mbp1.nomenclature.repository.ProductRepository;
import ru.namazov.mbp1.order.entity.Order;
import ru.namazov.mbp1.order.entity.OrderProduct;
import ru.namazov.mbp1.order.repository.OrderProductRepository;
import ru.namazov.mbp1.order.repository.OrderRepository;
import ru.namazov.mbp1.repository.StorageProductRepository;
import ru.namazov.mbp1.storage.model.Storage;
import ru.namazov.mbp1.storage.model.StorageProduct;
import ru.namazov.mbp1.storage.repository.StorageRepository;

@Component
public class ClientDBTesting {
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final StorageRepository storageRepository;
    private final StorageProductRepository storageProductRepository;
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final InvoiceRepository invoiceRepository;
    private final InvoiceProductRepository invoiceProductRepository;

    public ClientDBTesting(ClientRepository clientRepository, ProductRepository productRepository,
            StorageRepository storageRepository, StorageProductRepository storageProductRepository,
            OrderRepository orderRepository, OrderProductRepository orderProductRepository,
            InvoiceRepository invoiceRepository, InvoiceProductRepository invoiceProductRepository) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.storageRepository = storageRepository;
        this.storageProductRepository = storageProductRepository;
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.invoiceRepository = invoiceRepository;
        this.invoiceProductRepository = invoiceProductRepository;
        makeProduct();
    }

    private void makeProduct() {
        Product product1 = new Product();
        product1.setName("Мясо Оленины");
        product1.setMeasurement("кг");
        product1.setDescription("Замороженное");
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Мясо Оленины");
        product2.setMeasurement("кг");
        product2.setDescription("Охлажденное");
        productRepository.save(product2);

        Product product3 = new Product();
        product3.setName("Мясо Свиное");
        product3.setMeasurement("кг");
        product3.setDescription("Охлажденное");
        productRepository.save(product3);

        Product product4 = new Product();
        product4.setName("Мясо Баранье");
        product4.setMeasurement("кг");
        product4.setDescription("Охлажденное");
        productRepository.save(product4);

        Product product5 = new Product();
        product5.setName("Говяжий Стейк");
        product5.setMeasurement("шт");
        product5.setDescription("Стейк с жирком на кости 200-250г");
        productRepository.save(product5);



        Storage storage = new Storage();
        storage.setName("на Волгоградке");
        storageRepository.save(storage);

        StorageProduct storageProduct1 = new StorageProduct();
        storageProduct1.setProduct(product1);
        storageProduct1.setCount(10L);
        storageProduct1.setStorage(storage);
        storageProductRepository.save(storageProduct1);

        StorageProduct storageProduct2 = new StorageProduct();
        storageProduct2.setProduct(product2);
        storageProduct2.setCount(15L);
        storageProduct2.setStorage(storage);
        storageProductRepository.save(storageProduct2);

        StorageProduct storageProduct3 = new StorageProduct();
        storageProduct3.setProduct(product3);
        storageProduct3.setCount(20L);
        storageProduct3.setStorage(storage);
        storageProductRepository.save(storageProduct3);

        StorageProduct storageProduct4 = new StorageProduct();
        storageProduct4.setProduct(product4);
        storageProduct4.setCount(5L);
        storageProduct4.setStorage(storage);
        storageProductRepository.save(storageProduct4);

        StorageProduct storageProduct5 = new StorageProduct();
        storageProduct5.setProduct(product5);
        storageProduct5.setCount(40L);
        storageProduct5.setStorage(storage);
        storageProductRepository.save(storageProduct5);




        Client client = new Client();
        client.setName("ООО РОМАШКА");
        client.setTelNumber("8-926-946-82-87");
        client.setContactMen("Александр");
        client.setEmail("delluiza@yandex.ru");
        clientRepository.save(client);

        Client client1 = new Client();
        client1.setName("ООО СТРОЙДОМ11111111111111111111111111111111");
        client1.setTelNumber("8-993-793-15-00");
        client1.setContactMen("Сергей");
        client1.setEmail("Konfetka13-94@mail.ru");
        clientRepository.save(client1);

        Client client2 = new Client();
        client2.setName("ООО МЕРКУРИЙ");
        client2.setTelNumber("8-915-920-77-93");
        client2.setContactMen("Егор");
        client2.setEmail("alpotehinj@gmail.com");
        clientRepository.save(client2);

        Client client3 = new Client();
        client3.setName("ООО МЕРКУРИЙ");
        client3.setTelNumber("8-915-920-77-93");
        client3.setContactMen("Егор");
        client3.setEmail("alpotehinj@gmail.com");
        clientRepository.save(client3);

        Client client4 = new Client();
        client4.setName("ООО МЕРКУРИЙ");
        client4.setTelNumber("8-915-920-77-93");
        client4.setContactMen("Егор");
        client4.setEmail("alpotehinj@gmail.com");
        clientRepository.save(client4);

        Client client5 = new Client();
        client5.setName("ООО МЕРКУРИЙ");
        client5.setTelNumber("8-915-920-77-93");
        client5.setContactMen("Егор");
        client5.setEmail("alpotehinj@gmail.com");
        clientRepository.save(client5);

        Client client6 = new Client();
        client6.setName("ООО МЕРКУРИЙ");
        client6.setTelNumber("8-915-920-77-93");
        client6.setContactMen("Егор");
        client6.setEmail("alpotehinj@gmail.com");
        clientRepository.save(client6);

        Client client7 = new Client();
        client7.setName("ООО МЕРКУРИЙ");
        client7.setTelNumber("8-915-920-77-93");
        client7.setContactMen("Егор");
        client7.setEmail("alpotehinj@gmail.com");
        clientRepository.save(client7);

        Client client8 = new Client();
        client8.setName("ООО МЕРКУРИЙ");
        client8.setTelNumber("8-915-920-77-93");
        client8.setContactMen("Егор");
        client8.setEmail("alpotehinj@gmail.com");
        clientRepository.save(client8);

        Client client9 = new Client();
        client9.setName("ООО МЕРКУРИЙ");
        client9.setTelNumber("8-915-920-77-93");
        client9.setContactMen("Егор");
        client9.setEmail("alpotehinj@gmail.com");
        clientRepository.save(client9);




        Order order1 = new Order();
        order1.setDate(new Date(2023,8, 8));
        order1.setTimeFrom(LocalTime.of(10, 30));
        order1.setTimeTo(LocalTime.of(20, 30));
        order1.setAddress("Москва, Проспект мира, д.22");
        order1.setContact("Александр");
        order1.setTelNumber("8-923-123-43-92");
        order1.setClient(client);

        orderRepository.save(order1);

        OrderProduct orderProduct1 = new OrderProduct();
        orderProduct1.setProduct(product1);
        orderProduct1.setCount(5L);
        orderProduct1.setOrder(order1);

        OrderProduct orderProduct2 = new OrderProduct();
        orderProduct2.setProduct(product2);
        orderProduct2.setCount(5L);
        orderProduct2.setOrder(order1);

        OrderProduct orderProduct3 = new OrderProduct();
        orderProduct3.setProduct(product3);
        orderProduct3.setCount(5L);
        orderProduct3.setOrder(order1);

        orderProductRepository.save(orderProduct1);
        orderProductRepository.save(orderProduct3);
        orderProductRepository.save(orderProduct3);



        Invoice invoice = new Invoice();
        invoice.setNumber(456L);
        invoice.setDate(new Date(2023,9,17));
        invoice.setInStock(false);
        invoice.setReceived(false);
        invoice.setPaid(false);

        invoiceRepository.save(invoice);

        InvoiceProduct invoiceProduct1 = new InvoiceProduct();
        invoiceProduct1.setProduct(product1);
        invoiceProduct1.setCount(3L);
        invoiceProduct1.setInvoice(invoice);

        InvoiceProduct invoiceProduct2 = new InvoiceProduct();
        invoiceProduct2.setProduct(product2);
        invoiceProduct2.setCount(5L);
        invoiceProduct2.setInvoice(invoice);

        InvoiceProduct invoiceProduct3 = new InvoiceProduct();
        invoiceProduct3.setProduct(product3);
        invoiceProduct3.setCount(7L);
        invoiceProduct3.setInvoice(invoice);

        invoiceProductRepository.save(invoiceProduct1);
        invoiceProductRepository.save(invoiceProduct2);
        invoiceProductRepository.save(invoiceProduct3);
    }
}
