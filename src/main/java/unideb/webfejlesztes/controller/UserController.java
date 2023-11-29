package unideb.webfejlesztes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
