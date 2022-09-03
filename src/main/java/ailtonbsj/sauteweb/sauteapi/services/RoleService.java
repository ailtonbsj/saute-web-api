package ailtonbsj.sauteweb.sauteapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ailtonbsj.sauteweb.sauteapi.dto.RoleDTO;
import ailtonbsj.sauteweb.sauteapi.entities.Role;
import ailtonbsj.sauteweb.sauteapi.entities.User;
import ailtonbsj.sauteweb.sauteapi.repositories.UserRepository;

@Service
public class RoleService {

    @Autowired
    UserRepository userRepository;

    public User createRoleUser(RoleDTO roleDTO) {
        Optional<User> userExists = userRepository.findById(roleDTO.getIdUser());
        if(userExists.isEmpty()) throw new Error("User does not exists!");
        List<Role> roles = new ArrayList<>();
        roles = roleDTO.getIdsRoles().stream().map(
            role -> { return new Role(role); }
        ).collect(Collectors.toList());
        User user = userExists.get();
        user.setRoles(roles);
        userRepository.save(user);
        return user;
    }
}
