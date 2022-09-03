package ailtonbsj.sauteweb.sauteapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ailtonbsj.sauteweb.sauteapi.entities.User;
import ailtonbsj.sauteweb.sauteapi.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    private BCryptPasswordEncoder passEncoder() {
        return new BCryptPasswordEncoder();
    }

    public User createUser(User user) {
        User existUser = userRepository.findByUsername(user.getUsername());
        if(existUser != null) throw new Error("User already exists!");
        user.setPassword(passEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }
}
