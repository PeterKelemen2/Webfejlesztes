package unideb.webfejlesztes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unideb.webfejlesztes.model.House;

@Repository
public interface HouseRepository extends JpaRepository<Long, House> {

}
