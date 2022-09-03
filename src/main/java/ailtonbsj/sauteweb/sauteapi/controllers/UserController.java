package ailtonbsj.sauteweb.sauteapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ailtonbsj.sauteweb.sauteapi.dto.RoleDTO;
import ailtonbsj.sauteweb.sauteapi.entities.User;
import ailtonbsj.sauteweb.sauteapi.services.RoleService;
import ailtonbsj.sauteweb.sauteapi.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @PostMapping("/create")
    public User create(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/role")
    public User role(@RequestBody RoleDTO roleDTO) {
        return roleService.createRoleUser(roleDTO);
    }
}
