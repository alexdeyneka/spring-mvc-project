package com.mvc.service.controller;

import com.mvc.service.entity.User;
import com.mvc.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class NavigationController {

    private final UserService userService;
    private static final String ERROR = "error";

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password, Model model) {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        Optional<User> optionalUser = userService.save(user);
        if (!optionalUser.isPresent()) {
            return ERROR;
        }
        model.addAttribute("info", "User was registered successfully");
        return "success";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String welcome(@RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password, Model model) {
        String result = "";
        List<User> list = userService.findAll();
        if (list.isEmpty()) {
            model.addAttribute("info", "Access DENIED: invalid credentials");
            return ERROR;
        }
        for (User user : list) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                model.addAttribute("userName", userName);
                model.addAttribute("password", password);
                return "welcome";
            } else {
                model.addAttribute("info", "Access DENIED: invalid credentials");
                model.addAttribute("userName", userName);
                model.addAttribute("password", password);
                result = ERROR;
            }
        }
        return result;
    }
}
