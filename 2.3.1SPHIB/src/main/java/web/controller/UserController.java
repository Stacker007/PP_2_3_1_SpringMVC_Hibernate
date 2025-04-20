package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String users(Model model) {

        model.addAttribute("people", userService.getAllUsers());
        return "users/index";
    }

    @GetMapping("/user")
    public String user(@RequestParam(required = false) int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "users/user";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);

        return "users/edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("user") User user, @RequestParam int id) {
        userService.updateUser(id, user);
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
