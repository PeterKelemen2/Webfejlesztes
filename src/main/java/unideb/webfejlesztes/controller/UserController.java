package unideb.webfejlesztes.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import unideb.webfejlesztes.dto.UserDTO;
import unideb.webfejlesztes.model.House;
import unideb.webfejlesztes.model.User;
import unideb.webfejlesztes.service.HouseService;
import unideb.webfejlesztes.service.UserNotFoundException;
import unideb.webfejlesztes.service.UserService;

import java.util.List;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private HouseService houseService;

    @GetMapping("/users")
    public String showUserList(Model model) {
        List<User> userList = userService.listAll();
        model.addAttribute("userList", userList);
        return "users";
    }

    //ResponseEntity<?>
    @GetMapping("/users/{id}/houses")
    public String showUserHouses(@PathVariable String id, Model model) {
//        return ResponseEntity.ok(houseService.getHouseByOwnerId(userService.getUserById(Long.parseLong(id))));
        List<House> houseList = houseService.getHouseByOwnerId(userService.getUserById(Long.parseLong(id)));
        model.addAttribute("houseList", houseList);
        model.addAttribute("user_id", Long.parseLong(id));
        return "houses";

    }

    @GetMapping("/users/{id}/houses/new")
    public String showNewHouseForm(@PathVariable String id, Model model) {
        model.addAttribute("house", new House());
        model.addAttribute("user_id", Long.parseLong(id));
        model.addAttribute("pageTitle", "Add new House");
        return "house_form";
    }

    @PostMapping("/users/{id}/houses/save")
    public String saveHouse(Model model, House house, RedirectAttributes ra, @PathVariable String id) {

        house.setOwner(userService.getUserById(Long.parseLong(id)));
        model.addAttribute("user_id", Long.parseLong(id));
        houseService.save(house);

        ra.addFlashAttribute("message", "House saved successfully!");
        return "redirect:/users/{id}/houses";
    }

    @PostMapping("/users/{id}/houses/save/{house_id}")
    public String saveEditedHouse(Model model, House house, RedirectAttributes ra, @PathVariable String id, @PathVariable String house_id) {

        house.setOwner(userService.getUserById(Long.parseLong(id)));
        model.addAttribute("user_id", Long.parseLong(id));
        model.addAttribute("house_id", Long.parseLong(house_id));
        houseService.save(house);

        ra.addFlashAttribute("message", "House saved successfully!");
        return "redirect:/users/{id}/houses";
    }

    @GetMapping("/users/new")
    public String showNewForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add New User");
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra) {
        userService.save(user);

        ra.addFlashAttribute("message", "User saved successfully!");
        return "redirect:/users";
    }



    @PostMapping("/users/create")
    public void createUser(@RequestBody UserDTO body) {
        userService.createUser(body);
    }

    @GetMapping("/users/get/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id) {
        var user = userService.getUserById(Long.parseLong(id));
        if (user == null) throw new RuntimeException("Error");
        return ResponseEntity.ok(UserDTO.fromDao(user));
    }

    @PutMapping("/users/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody UserDTO body) {
        var user = userService.getUserById(Long.parseLong(id));
        if (user == null) throw new RuntimeException("Error");

        user.setUsername(body.name());
        userService.save(user);

        return ResponseEntity.ok(UserDTO.fromDao(user));
    }

    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        userService.deleteUserById(Long.parseLong(id));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/{id}/house/delete/{house_id}")
    public String deleteHouse(@PathVariable String id, @PathVariable String house_id, RedirectAttributes ra) {
        houseService.deleteHouseById(Long.parseLong(house_id));
        ra.addFlashAttribute("message", "The House ID " + id + " has been deleted.");

        return "redirect:/users/{id}/houses";
    }

    @GetMapping("/users/{id}/houses/edit/{house_id}")
    public String showHouseEditForm(@PathVariable("id") Long id, @PathVariable("house_id") Long house_id, Model model, RedirectAttributes ra){
        House house = houseService.getHouseById(house_id);
        model.addAttribute("house", house);
        model.addAttribute("user_id", id);
        model.addAttribute("house_id", house_id);
        model.addAttribute("pageTitle", "Edit House (ID: " + house_id + ")");

        return "house_edit_form";
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
