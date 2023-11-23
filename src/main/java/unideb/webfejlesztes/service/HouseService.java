package unideb.webfejlesztes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import unideb.webfejlesztes.model.House;
import unideb.webfejlesztes.repository.HouseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HouseService {
    private final HouseRepository houseRepository;

    public void createHouse(String address, int houseNum, int zip, int price, double area) {
//        var existingHouse = houseRepository.getByAddress(address);
//        if (existingHouse.isPresent()) throw new RuntimeException("error");
        houseRepository.save(new House(address, houseNum, zip, price, area));
    }

    public List<House> getHouse() {
        return houseRepository.findAll();
    }
}
