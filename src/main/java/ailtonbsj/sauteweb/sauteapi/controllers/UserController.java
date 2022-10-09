package ailtonbsj.sauteweb.sauteapi.controllers;

import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ailtonbsj.sauteweb.sauteapi.dto.RoleDTO;
import ailtonbsj.sauteweb.sauteapi.entities.GoogleToken;
import ailtonbsj.sauteweb.sauteapi.entities.User;
import ailtonbsj.sauteweb.sauteapi.security.UserPrincipal;
import ailtonbsj.sauteweb.sauteapi.security.jwt.JWTAuthenticationFilter;
import ailtonbsj.sauteweb.sauteapi.services.RoleService;
import ailtonbsj.sauteweb.sauteapi.services.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin({ "${app.front-url}" })
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    // @PostMapping("/login")
    // public User login(@RequestBody User user) {
    // return userService.doLogin(user);
    // }

    @PostMapping("/create")
    public User create(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/role")
    public User role(@RequestBody RoleDTO roleDTO) {
        return roleService.createRoleUser(roleDTO);
    }

    @PostMapping("/with-google")
    public String withGoogle(@RequestBody String token, HttpServletRequest request)
            throws JsonMappingException, JsonProcessingException {
        String json = restTemplateBuilder.build().getForObject(
                "https://oauth2.googleapis.com/tokeninfo?id_token=" + token, String.class);
        ObjectMapper mapper = new ObjectMapper();
        GoogleToken parsed = mapper.readValue(json, GoogleToken.class);
        UserPrincipal login = UserPrincipal.create(
                userService.doLoginWithGoogle(parsed.getEmail()));

        String issuer = request.getRequestURL().toString();
        String ownToken = JWT.create().withSubject(login.getUsername()).withExpiresAt(
                new Date(System.currentTimeMillis() + JWTAuthenticationFilter.TOKEN_EXPIRATION))
                .withClaim("roles", login.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withIssuer(issuer).sign(Algorithm.HMAC512(JWTAuthenticationFilter.SECRET.getBytes()));

        return String.format("{\"token\":\"%s\"}", ownToken);
    }
}
