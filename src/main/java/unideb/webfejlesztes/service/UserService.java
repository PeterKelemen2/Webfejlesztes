package unideb.webfejlesztes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import unideb.webfejlesztes.dto.UserDTO;
import unideb.webfejlesztes.model.User;
import unideb.webfejlesztes.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> listAll(){
        return userRepository.findAll();
    }

    public User getUserById(long l) {
        return userRepository.findById(l).orElse(null);
    }

    public void save(User user) {userRepository.save(user);}

    public void createUser(UserDTO body) {
        User newUser = new User(
                body.name());
        userRepository.save(newUser);
    }


    public void deleteUserById(long l) {
        userRepository.deleteById(l);
    }


}

