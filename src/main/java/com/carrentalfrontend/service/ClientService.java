package com.carrentalfrontend.service;

import com.carrentalfrontend.client.ClientClient;
import com.carrentalfrontend.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<ClientDto> getAllClients() {
        return clientClient.getAllClients();
    }

    public void saveNewClient(ClientDto clientDto) {
        clientClient.saveNewClient(clientDto);
    }

    public void updateClient(ClientDto clientDto) {
        clientClient.updateClient(clientDto);
    }

    public void deleteClient(Long clientId) {
        clientClient.deleteCar(clientId);
    }
}
