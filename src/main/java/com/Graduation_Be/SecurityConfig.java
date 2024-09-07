package com.Graduation_Be;

import com.Graduation_Be.config.JwtConfig;
import com.nimbusds.jose.JOSEException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Autowired
    private JwtConfig jwtConfig;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(https -> https.disable())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/swagger-ui/**","/api-docs/**", "/swagger-ui.html","/auth/**").permitAll()
                        .requestMatchers("/user/**").hasRole("admin")
                        .anyRequest().authenticated()
                )
                .formLogin(logn -> logn.disable())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> {
                            try {
                                jwt
                                        .decoder(jwtConfig.jwtDecoder())
                                        .jwtAuthenticationConverter(jwtAuthenticationConverter());
                            } catch (JOSEException e) {
                                throw new RuntimeException(e);
                            }
                        }
                ))
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .addLogoutHandler(new SecurityContextLogoutHandler())
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.setStatus(HttpServletResponse.SC_OK);
                            response.getWriter().write("{\"message\":\"Đăng xuất thành công\"}");
                            response.setContentType("application/json");
                        })
                );
        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider) throws Exception {
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthoritiesClaimName("role");
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

}