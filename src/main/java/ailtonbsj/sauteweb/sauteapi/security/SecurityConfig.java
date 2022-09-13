package ailtonbsj.sauteweb.sauteapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ailtonbsj.sauteweb.sauteapi.security.jwt.JWTAuthenticationFilter;
import ailtonbsj.sauteweb.sauteapi.security.jwt.JWTAuthorizationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    @Autowired
    AuthenticationConfiguration authenticationConfiguration;

    // @Autowired
    // CustomUserDetailsService userService;

    // @Autowired
    // PasswordEncoder passwordEncoder;

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

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter(
                authenticationConfiguration.getAuthenticationManager());
        jwtAuthenticationFilter.setFilterProcessesUrl("/api/login");

        http.csrf().disable().cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().antMatchers("/api/login/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    // @Bean
    // CorsConfigurationSource corsConfigurationSource() {
    //     final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
    //     source.registerCorsConfiguration("/**", corsConfiguration);
    //     return source;
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
