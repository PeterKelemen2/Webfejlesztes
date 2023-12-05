package unideb.webfejlesztes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "houses")
@NoArgsConstructor
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private Integer houseNum;
    private Integer zip;
    private Integer price;
    private Double area;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;

    public House(String address, int houseNum, int zip, int price, double area) {
        this.address = address;
        this.houseNum = houseNum;
        this.zip = zip;
        this.price = price;
        this.area = area;
    }
}
