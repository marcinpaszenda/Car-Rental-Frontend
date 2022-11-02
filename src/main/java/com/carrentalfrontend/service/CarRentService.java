package com.carrentalfrontend.service;

import com.carrentalfrontend.client.CarRentClient;
import com.carrentalfrontend.dto.CarDto;
import com.carrentalfrontend.dto.CarRentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarRentService {

        private static CarRentService carRentService;
        private final CarRentClient carRentClient;

        public static CarRentService getInstance() {
            if (carRentService == null) {
                carRentService = new CarRentService(CarRentClient.getInstance());
            }
            return carRentService;
        }

        public List<CarRentDto> getAllCarRents() {
            return carRentClient.getAllCarRents();
        }

        public void saveNewCarRent(CarRentDto carRentDto) {
            carRentClient.saveNewCarRent(carRentDto);
        }

        public void updateCarRent(CarRentDto carRentDto) {
            carRentClient.updateCarRent(carRentDto);
        }

        public void deleteCarRent(Long carRentId) {
            carRentClient.deleteCarRent(carRentId);
        }
}
