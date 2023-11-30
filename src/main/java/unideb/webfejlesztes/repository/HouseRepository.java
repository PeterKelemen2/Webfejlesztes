package unideb.webfejlesztes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unideb.webfejlesztes.model.House;
import unideb.webfejlesztes.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {

    Optional<House> getByAddress(String address);

    List<House> findAllByOwner(User user);
}
