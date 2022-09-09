package ailtonbsj.sauteweb.sauteapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ailtonbsj.sauteweb.sauteapi.security.jwt.JWTAuthenticationFilter;
import ailtonbsj.sauteweb.sauteapi.security.jwt.JWTValidateFilter;

// @EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailsService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTValidateFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    // return new BCryptPasswordEncoder();
    // }

    // @Bean
    // public AuthenticationManager
    // authenticationManager(AuthenticationConfiguration
    // authenticationConfiguration)
    // throws Exception {
    // return authenticationConfiguration.getAuthenticationManager();
    // }

    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // http.cors().and().csrf().disable()
    // .authorizeRequests()
    // .antMatchers(HttpMethod.POST, "/users/create", "/users/create/**")
    // .permitAll()
    // .anyRequest().authenticated().and()
    // .addFilter(new JWTAuthenticationFilter(authenticationManager()))
    // .addFilter(new JWTAuthenticationFilter(authenticationManager()))
    // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    // return http.build();
    // }

    // @Bean
    // public WebMvcConfigurer corsConfigurer() {
    // return new WebMvcConfigurer() {
    // @Override
    // public void addCorsMappings(CorsRegistry registry) {
    // registry.addMapping("/**")
    // .allowedMethods("*");
    // }
    // };
    // }
}
