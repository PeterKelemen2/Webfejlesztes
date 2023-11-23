package unideb.webfejlesztes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import unideb.webfejlesztes.model.House;
import unideb.webfejlesztes.repository.HouseRepository;

@Service
@RequiredArgsConstructor
public class HouseService {
    private final HouseRepository houseRepository;

    public void createHouse(String address, int zip, int price, double area) {
//        var existingHouse = houseRepository.getByAddress(address);
//        if (existingHouse.isPresent()) throw new RuntimeException("GECI");
        houseRepository.save(new House(address, zip, price, area));
    }

}
