package unideb.webfejlesztes.dto;

import unideb.webfejlesztes.model.House;

public record HouseDTO(
        long id,
        String address,
        int number,
        int zip,
        int price,
        double area
) {
    public static HouseDTO fromDao(House house) {
        return new HouseDTO(
                house.getId(),
                house.getAddress(),
                house.getHouseNum(),
                house.getZip(),
                house.getPrice(),
                house.getArea()
        );
    }
}
