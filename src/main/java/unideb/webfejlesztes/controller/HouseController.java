package unideb.webfejlesztes.controller;

import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unideb.webfejlesztes.dto.HouseDTO;
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
        log.info("anyad: {}", h);
        return ResponseEntity.ok(h.stream()
                .map(HouseDTO::fromDao).toList());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createHouse(){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getHouse(@PathVariable String id) {
        var hous = houseService.getHouseById(Long.parseLong(id));
        if (hous == null) throw new RuntimeException("nincs vityilló");
        return ResponseEntity.ok(HouseDTO.fromDao(hous));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateHouse(@PathVariable String id, @RequestBody HouseDTO body) {
        var house = houseService.getHouseById(Long.parseLong(id));
        if (house == null) throw new RuntimeException("nincs vityilló");

        house.setHouseNum(body.number());
        house.setPrice(body.price());
        house.setArea(body.area());
        houseService.save(house);

        return ResponseEntity.ok(HouseDTO.fromDao(house));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteHouse(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }
}
