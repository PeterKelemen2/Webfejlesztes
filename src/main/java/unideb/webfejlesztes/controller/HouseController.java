package unideb.webfejlesztes.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import unideb.webfejlesztes.dto.HouseDTO;
import unideb.webfejlesztes.model.House;
import unideb.webfejlesztes.service.HouseService;

@Slf4j
@RestController
@RequestMapping("/houses")
public class HouseController {
    @Autowired
    private HouseService houseService;

    @GetMapping("/house-list")
    public ResponseEntity<?> getHouses(){
        var h = houseService.getHouse();
        return ResponseEntity.ok(h.stream()
                .map(HouseDTO::fromDao).toList());
    }

    @PostMapping("/create")
    public void createHouse(@RequestBody HouseDTO body) {
        houseService.createHouse(body);
    }

    @GetMapping("/new")
    public String showNewForm(Model model){
        model.addAttribute("house", new House());
        model.addAttribute("pageTitle", "Add New house");
        return "house_form";
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getHouse(@PathVariable String id) {
        var house = houseService.getHouseById(Long.parseLong(id));
        if (house == null) throw new RuntimeException("Error");
        return ResponseEntity.ok(HouseDTO.fromDao(house));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateHouse(@PathVariable String id, @RequestBody HouseDTO body) {
        var house = houseService.getHouseById(Long.parseLong(id));
        if (house == null) throw new RuntimeException("Error");

        house.setHouseNum(body.number());
        house.setPrice(body.price());
        house.setArea(body.area());
        houseService.save(house);

        return ResponseEntity.ok(HouseDTO.fromDao(house));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteHouse(@PathVariable String id) {
        houseService.deleteHouseById(Long.parseLong(id));
        return ResponseEntity.ok().build();
    }

}
