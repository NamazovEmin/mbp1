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

import org.springframework.stereotype.Component;

import ru.namazov.mbp1.model.Client;
import ru.namazov.mbp1.model.Product;
import ru.namazov.mbp1.repository.ClientRepository;
import ru.namazov.mbp1.repository.ProductRepository;

@Component
public class ClientDBTesting {
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    public ClientDBTesting(ClientRepository clientRepository, ProductRepository productRepository) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        makeClient();
        makeProduct();
    }

    private void makeProduct() {
        Product product = new Product();
        product.setName("Мясо Оленины");
        product.setCount(20L);
        product.setMeasurement("кг");
        product.setDescription("Замороженное");
        productRepository.save(product);

        Product product1 = new Product();
        product1.setName("Мясо Оленины");
        product1.setCount(10L);
        product1.setMeasurement("кг");
        product1.setDescription("Охлажденное");
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Мясо Свиное");
        product2.setCount(10L);
        product2.setMeasurement("кг");
        product2.setDescription("Охлажденное");
        productRepository.save(product2);

        Product product3 = new Product();
        product3.setName("Мясо Баранье");
        product3.setCount(25L);
        product3.setMeasurement("кг");
        product3.setDescription("Охлажденное");
        productRepository.save(product3);

        Product product4 = new Product();
        product4.setName("Говяжий Стейк");
        product4.setCount(7L);
        product4.setMeasurement("шт");
        product4.setDescription("Стейк с жирком на кости 200-250г");
        productRepository.save(product4);
    }

    private void makeClient() {
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

        Client client10 = new Client();
        client10.setName("ООО МЕРКУРИЙ");
        client10.setTelNumber("8-915-920-77-93");
        client10.setContactMen("Егор");
        client10.setEmail("alpotehinj@gmail.com");
        clientRepository.save(client10);

        Client client11 = new Client();
        client11.setName("ООО МЕРКУРИЙ");
        client11.setTelNumber("8-915-920-77-93");
        client11.setContactMen("Егор");
        client11.setEmail("alpotehinj@gmail.com");
        clientRepository.save(client11);

        Client client12 = new Client();
        client12.setName("ООО МЕРКУРИЙ");
        client12.setTelNumber("8-915-920-77-93");
        client12.setContactMen("Егор");
        client12.setEmail("alpotehinj@gmail.com");
        clientRepository.save(client12);

        Client client13 = new Client();
        client13.setName("ООО МЕРКУРИЙ");
        client13.setTelNumber("8-915-920-77-93");
        client13.setContactMen("Егор");
        client13.setEmail("alpotehinj@gmail.com");
        clientRepository.save(client13);

        Client client14 = new Client();
        client14.setName("ООО МЕРКУРИЙ");
        client14.setTelNumber("8-915-920-77-93");
        client14.setContactMen("Егор");
        client14.setEmail("alpotehinj@gmail.com");
        clientRepository.save(client14);

        Client client15 = new Client();
        client15.setName("ООО МЕРКУРИЙ");
        client15.setTelNumber("8-915-920-77-93");
        client15.setContactMen("Егор");
        client15.setEmail("alpotehinj@gmail.com");
        clientRepository.save(client15);

        Client client16 = new Client();
        client16.setName("ООО МЕРКУРИЙ");
        client16.setTelNumber("8-915-920-77-93");
        client16.setContactMen("Егор");
        client16.setEmail("alpotehinj@gmail.com");
        clientRepository.save(client16);

        Client client17 = new Client();
        client17.setName("ООО МЕРКУРИЙ");
        client17.setTelNumber("8-915-920-77-93");
        client17.setContactMen("Егор");
        client17.setEmail("alpotehinj@gmail.com");
        clientRepository.save(client17);
    }
}
