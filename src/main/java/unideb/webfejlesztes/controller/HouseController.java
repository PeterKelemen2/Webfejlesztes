package unideb.webfejlesztes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unideb.webfejlesztes.model.House;
import unideb.webfejlesztes.repository.HouseRepository;
import unideb.webfejlesztes.service.HouseService;

import java.util.List;

@RestController
@RequestMapping("/houses")
public class HouseController {
    @Autowired
    private HouseService houseService;

    @GetMapping("/house-list")
    public List<House> getHouses(){
        // TODO: AAAAAAAAAAAAA
        List<House> myHouses = houseService.getHouse();
        return myHouses;
    }


}
