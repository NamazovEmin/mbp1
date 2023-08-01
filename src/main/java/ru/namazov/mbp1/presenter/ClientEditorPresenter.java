package ru.namazov.mbp1.presenter;

import org.springframework.stereotype.Component;

import ru.namazov.mbp1.model.Client;
import ru.namazov.mbp1.repository.ClientRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class ClientEditorPresenter {

    private final ClientRepository clientRepository;

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow();
    }
}
