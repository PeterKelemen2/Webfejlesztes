package unideb.webfejlesztes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unideb.webfejlesztes.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Long, User> {

    Optional<User> findByUsername(String username);
}
