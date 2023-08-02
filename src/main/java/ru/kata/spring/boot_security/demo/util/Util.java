package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class Util {
    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public Util(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void initialization() {
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");

        roleService.save(adminRole);
        roleService.save(userRole);
        User admin = new User("Admin", "Admin", (byte)10, "admin", "admin", Set.of(adminRole, userRole));
        userService.addUser(admin);

        roleService.save(userRole);
        User user = new User("User","User", (byte)10, "user", "user", Set.of(userRole));
        userService.addUser(user);
    }
}
