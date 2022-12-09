package com.carrentalfrontend.service;

import com.carrentalfrontend.client.ClientClient;
import com.carrentalfrontend.domain.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

    private static ClientService clientService;
    private final ClientClient clientClient;

    public static ClientService getInstance() {
        if (clientService == null) {
            clientService = new ClientService(ClientClient.getInstance());
        }
        return clientService;
    }

    public List<Client> getAllClients() {
        return clientClient.getAllClients();
    }

    public void saveNewClient(Client client) {
        clientClient.saveNewClient(client);
    }

    public void updateClient(Client client) {
        clientClient.updateClient(client);
    }

    public void deleteClient(Long clientId) {
        clientClient.deleteCar(clientId);
    }

    public List<Client> findByName(String name) {
        return (clientClient.getAllClients().stream()
                .filter(client -> client.getName().toLowerCase().contains(name) ||
                        client.getName().toUpperCase().contains(name))
                .collect(Collectors.toList()));
    }
}
