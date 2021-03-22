package unnoba.poo2020.hotel.controller;

import unnoba.poo2020.hotel.model.User;
import unnoba.poo2020.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 Este controlador maneja la gestión de usuarios
 */
@Controller
@RequestMapping("/users")  // Mapeamos las HTTP request a /users a la clase controlador UserController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping("/new")
    public String userNew(Model model){
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping
    public String create(@ModelAttribute User user){
        userService.create(user);
        return "redirect:/login"; // Cuando creamos un usuario lo va a redireccionar al login
    }

    @GetMapping
    public String getUsers(Model model){
        model.addAttribute("users",userService.getUsers());
        return "redirect:/";
    }

}
