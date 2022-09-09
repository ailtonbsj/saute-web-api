package ailtonbsj.sauteweb.sauteapi.security.jwt;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTValidateFilter extends BasicAuthenticationFilter {

    public static final String HEADER_ATTR = "Authorization";
    public static final String PREFIX_ATTR = "Bearer ";

    public JWTValidateFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String attr = request.getHeader(HEADER_ATTR);
        if (attr == null || !attr.startsWith(PREFIX_ATTR)) {
            chain.doFilter(request, response);
            return;
        }

        String token = attr.replace(PREFIX_ATTR, "");
        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
        DecodedJWT jwtDec = JWT.require(Algorithm.HMAC512(JWTAuthenticationFilter.SECRET)).build().verify(token);
        String user = jwtDec.getSubject();
        // String authorities = jwtDec.getClaims();
        if(user == null) return null;
        return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
    }
}
