package pe.org.group02.ventaboletoscine.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    @Autowired
    JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(HttpMethod.POST, "/user/login").permitAll()
                        //Usuarios
                        .requestMatchers(HttpMethod.POST, "/user/add").hasAnyRole("Operador", "Administrador")
                        .requestMatchers(HttpMethod.GET, "/user/find").hasAnyRole("Operador", "Administrador")
                        .requestMatchers(HttpMethod.POST, "/user/update").hasRole("Administrador")
                        .requestMatchers(HttpMethod.POST, "/user/delete").hasRole("Administrador")
                        //PeliculasGenero
                        .requestMatchers(HttpMethod.POST, "/movies/add").hasAnyRole("Operador", "Administrador")
                        .requestMatchers(HttpMethod.GET, "/movies/find").hasAnyRole("Operador", "Administrador")
                        .requestMatchers(HttpMethod.POST, "/movies/update").hasRole("Administrador")
                        .requestMatchers(HttpMethod.POST, "/movies/delete").hasRole("Administrador")
                        //Funciones
                        .requestMatchers(HttpMethod.POST, "/function/add").hasAnyRole("Operador", "Administrador")
                        .requestMatchers(HttpMethod.GET, "/function/find").hasAnyRole("Operador", "Administrador")
                        .requestMatchers(HttpMethod.POST, "/function/update").hasRole("Administrador")
                        .requestMatchers(HttpMethod.POST, "/function/delete").hasRole("Administrador")
                        //Empleados
                        .requestMatchers(HttpMethod.POST, "/employee/add").hasAnyRole("Operador", "Administrador")
                        .requestMatchers(HttpMethod.GET, "/employee/find").hasAnyRole("Operador", "Administrador")
                        .requestMatchers(HttpMethod.POST, "/employee/update").hasRole("Administrador")
                        .requestMatchers(HttpMethod.POST, "/employee/delete").hasRole("Administrador")
                        //Boletos
                        .requestMatchers(HttpMethod.POST, "/ticket/add").hasAnyRole("Operador", "Administrador")
                        .requestMatchers(HttpMethod.GET, "/ticket/find").hasAnyRole("Operador", "Administrador")
                        .requestMatchers(HttpMethod.POST, "/ticket/update").hasRole("Administrador")
                        .requestMatchers(HttpMethod.POST, "/ticket/delete").hasRole("Administrador")

                        .anyRequest().authenticated()
                )
                .addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
