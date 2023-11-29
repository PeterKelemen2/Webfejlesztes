package unideb.webfejlesztes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

}

