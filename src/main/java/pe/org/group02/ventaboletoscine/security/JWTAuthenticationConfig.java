package pe.org.group02.ventaboletoscine.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pe.org.group02.ventaboletoscine.entity.Usuarios;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static pe.org.group02.ventaboletoscine.security.Constants.*;

@Configuration
public class JWTAuthenticationConfig {

    public String getJWTToken(Usuarios usuario) {
        List<String> roles = Collections.singletonList(usuario.getIdEmpleado().getIdRol().getNombreRol());

        List<GrantedAuthority> grantedAuthorities = roles.stream()
                .map(rol -> new SimpleGrantedAuthority("ROLE_" + rol))
                .collect(Collectors.toList());


        String token = Jwts
                .builder()
                .setId(usuario.getUsuario())
                .setSubject(usuario.getUsuario())
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .signWith(getSigningKey(SUPER_SECRET_KEY), SignatureAlgorithm.HS512).compact();

        return "Bearer " + token;
    }

}
