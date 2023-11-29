package unideb.webfejlesztes.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
                .map(HouseDTO::fromDao)
                .toList()
        );
    }


}
