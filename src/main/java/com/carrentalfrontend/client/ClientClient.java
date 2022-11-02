package com.carrentalfrontend.client;

import com.carrentalfrontend.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClientClient {

    private final RestTemplate restTemplate;
    private static final String CLIENT_URL = "https://car-rental-lemarcar.herokuapp.com/v1/clients/";
    private static ClientClient clientClient;

    public static ClientClient getInstance() {
        if (clientClient == null) {
            clientClient = new ClientClient(new RestTemplate());
        }
        return clientClient;
    }

    public List<ClientDto> getAllClients() {
        try {
            ResponseEntity<ClientDto[]> responseEntity = restTemplate.getForEntity(CLIENT_URL, ClientDto[].class);
            return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public void saveNewClient(ClientDto clientDto) {
        try {
            restTemplate.postForObject(CLIENT_URL, clientDto, Void.class);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void updateClient(ClientDto clientDto) {
        try {
            restTemplate.put(CLIENT_URL, clientDto, Void.class);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void deleteCar(Long clientId) {
        try {
            restTemplate.delete(CLIENT_URL + clientId);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }
}
