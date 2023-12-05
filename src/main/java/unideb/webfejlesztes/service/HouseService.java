package unideb.webfejlesztes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import unideb.webfejlesztes.dto.HouseDTO;
import unideb.webfejlesztes.model.House;
import unideb.webfejlesztes.model.User;
import unideb.webfejlesztes.repository.HouseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HouseService {
    private final HouseRepository houseRepository;

    public List<House> getHouse() {
        return houseRepository.findAll();
    }

    public House getHouseById(long l) {
        return houseRepository.findById(l).orElse(null);
    }

    public List<House> getHouseByOwnerId(User user){
        return houseRepository.findAllByOwner(user);
    }

    public void save(House house) {
        houseRepository.save(house);
    }

    public void createHouse(HouseDTO body) {
        House newHouse = new House(
                body.address(),
                body.number(),
                body.zip(),
                body.price(),
                body.area());
        houseRepository.save(newHouse);
    }

    public void deleteHouseById(long id) {
        houseRepository.deleteById(id);
    }
}
