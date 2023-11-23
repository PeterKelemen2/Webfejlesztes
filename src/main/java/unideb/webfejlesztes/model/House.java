package unideb.webfejlesztes.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String address;
    private int zip;
    private int price;
    private double area;

    public House(String address, int zip, int price, double area) {
        this.address = address;
        this.zip = zip;
        this.price = price;
        this.area = area;
    }
}
