package unideb.webfejlesztes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import unideb.webfejlesztes.dto.UserDTO;
import unideb.webfejlesztes.model.User;
import unideb.webfejlesztes.service.UserService;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> userList = userService.listAll();
        model.addAttribute("userList", userList);
        return "users";
    }

    @GetMapping("/users/new")
    public String showNewForm(Model model){
        model.addAttribute("user", new User());
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user){
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id){
        var user = userService.getUserById(Long.parseLong(id));
        if (user == null) throw new RuntimeException("Error");
        return ResponseEntity.ok(UserDTO.fromDao(user));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody UserDTO body){
        var user = userService.getUserById(Long.parseLong(id));
        if (user == null) throw new RuntimeException("Error");

        user.setUsername(body.name());
        userService.save(user);

        return ResponseEntity.ok(UserDTO.fromDao(user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id){
        userService.deleteUserById(Long.parseLong(id));
        return ResponseEntity.ok().build();
    }
}
