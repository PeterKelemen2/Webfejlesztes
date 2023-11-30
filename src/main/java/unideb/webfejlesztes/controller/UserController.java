package unideb.webfejlesztes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import unideb.webfejlesztes.dto.UserDTO;
import unideb.webfejlesztes.model.User;
import unideb.webfejlesztes.service.UserNotFoundException;
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
        model.addAttribute("pageTitle", "Add New User");
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra){
        userService.save(user);

        ra.addFlashAttribute("message", "The user has been saved successfully!");
        return "redirect:/users";
    }

    @PostMapping("/users/create")
    public void createUser(@RequestBody UserDTO body){
        userService.createUser(body);
    }

    @GetMapping("/users/get/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id){
        var user = userService.getUserById(Long.parseLong(id));
        if (user == null) throw new RuntimeException("Error");
        return ResponseEntity.ok(UserDTO.fromDao(user));
    }

    @PutMapping("/users/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody UserDTO body){
        var user = userService.getUserById(Long.parseLong(id));
        if (user == null) throw new RuntimeException("Error");

        user.setUsername(body.name());
        userService.save(user);

        return ResponseEntity.ok(UserDTO.fromDao(user));
    }

    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id){
        userService.deleteUserById(Long.parseLong(id));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            User user = userService.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");

            return "user_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            userService.delete(id);
            ra.addFlashAttribute("message", "The user ID " + id + " has been deleted.");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";
    }

}
