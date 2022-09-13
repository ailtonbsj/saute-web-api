package ailtonbsj.sauteweb.sauteapi.security.jwt;

import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import ailtonbsj.sauteweb.sauteapi.entities.User;
import ailtonbsj.sauteweb.sauteapi.security.UserPrincipal;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

        @Autowired
        AuthenticationManager authenticationManager;

        public static final int TOKEN_EXPIRATION = 86_400_000;
        public static final String SECRET = "THIS_IS_YOUR_SECRET";

        @Override
        public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
                        throws AuthenticationException {
                try {
                        // Maping JSON Object to User Entity
                        User user = new ObjectMapper().readValue(
                                        request.getInputStream(), User.class);
                        return authenticationManager.authenticate(
                                        new UsernamePasswordAuthenticationToken(
                                                        user.getUsername(), user.getPassword()));
                } catch (IOException e) {
                        throw new RuntimeException("Falha ao autenticar ", e);
                }
        }

        @Override
        protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                        FilterChain chain,
                        Authentication authResult) throws IOException, ServletException {

                UserPrincipal userPrincipal = (UserPrincipal) authResult.getPrincipal();
                String issuer = request.getRequestURL().toString();
                String token = JWT.create().withSubject(userPrincipal.getUsername()).withExpiresAt(
                                new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                                .withClaim("roles", userPrincipal.getAuthorities().stream()
                                                .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                                .withIssuer(issuer).sign(Algorithm.HMAC512(SECRET.getBytes()));

                response.getWriter().write(String.format("{\"token\":\"%s\"}", token));
                response.getWriter().flush();
        }

}
