package unideb.webfejlesztes.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "houses")
@NoArgsConstructor
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String address;
    private int zip;
    private int price;
    private double area;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;

    public House(String address, int zip, int price, double area) {
        this.address = address;
        this.zip = zip;
        this.price = price;
        this.area = area;
    }
}
