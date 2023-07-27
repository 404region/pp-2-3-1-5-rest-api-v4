package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String adminPage(Model model, Principal principal, @ModelAttribute("newUser") User newUser) {
        User currUser = userService.findByUsername(principal.getName());
        // текущий пользователь
        model.addAttribute("currUser", currUser);
        model.addAttribute("currUserRoles", currUser.getRoles());
        // все пользователи
        model.addAttribute("allUsers", userService.getUsersList());
        model.addAttribute("allUsersRoles", roleService.getRolesList());
        return "admin";
    }

    @PostMapping("/admin")
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("admin/{id}/edit")
    public String editUser(@ModelAttribute("user") User user) {
//        User exUser = userService.getById(id);
//        exUser.setName(editedUser.getName());
//        exUser.setSurname(editedUser.getSurname());
//        exUser.setAge(editedUser.getAge());
//        exUser.setUsername(editedUser.getUsername());
//        exUser.setRoles(editedUser.getRoles());
        userService.editUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("admin/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
