package unideb.webfejlesztes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import unideb.webfejlesztes.dto.UserDTO;
import unideb.webfejlesztes.model.User;
import unideb.webfejlesztes.repository.UserRepository;

import java.util.List;
import java.util.Optional;

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

    public User get(Integer id) throws UserNotFoundException{
        Optional<User> result = userRepository.findById(id.longValue());
        if(result.isPresent()){
            return result.get();
        }
        else{
            throw new UserNotFoundException("User with " + id + " ID not found!");
        }
    }

    public void delete(Integer id) throws UserNotFoundException {
//        Long count = userRepository.findById(id.longValue());
//        if (count == null || count == 0) {
//            throw new UserNotFoundException("Could not find any users with ID " + id);
//        }
//        userRepository.deleteById(id);
        userRepository.deleteById(id.longValue());
    }
}

