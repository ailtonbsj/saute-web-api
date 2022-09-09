package ailtonbsj.sauteweb.sauteapi.security.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import ailtonbsj.sauteweb.sauteapi.entities.User;
import ailtonbsj.sauteweb.sauteapi.security.UserPrincipal;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final int TOKEN_EXPIRATION = 86_400_000;
    public static final String SECRET = "THIS_IS_YOUR_SECRET";

    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            // Maping Object to User Entity
            User user = new ObjectMapper().readValue(
                    request.getInputStream(), User.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException("Falha ao autenticar ", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        UserPrincipal userPrincipal = (UserPrincipal) authResult.getPrincipal();

        String token = JWT.create().withSubject(userPrincipal.getUsername()).withExpiresAt(
                new Date(System.currentTimeMillis() + TOKEN_EXPIRATION)).sign(Algorithm.HMAC512(SECRET));

        response.getWriter().write(token);
        response.getWriter().flush();
    }

}
