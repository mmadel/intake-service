package com.cob.salesforce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.DelegatingJwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        DelegatingJwtGrantedAuthoritiesConverter authoritiesConverter =
                // Using the delegating converter multiple converters can be combined
                new DelegatingJwtGrantedAuthoritiesConverter(
                        // First add the default converter
                        new JwtGrantedAuthoritiesConverter(),
                        // Second add our custom Keycloak specific converter
                        new KeycloakJwtRolesConverter());
        // Set up http security to use the JWT converter defined above
        httpSecurity.oauth2ResourceServer().jwt().jwtAuthenticationConverter(
                jwt -> new JwtAuthenticationToken(jwt, authoritiesConverter.convert(jwt)));

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.authorizeHttpRequests(authorize -> authorize
                        .antMatchers("/authentication/tokens","/requires/fields/retrieve/**").permitAll()
                        .antMatchers("/user/**").hasAnyRole("administrator", "normal")
                        .antMatchers("/patient/find/clinic/**").hasAnyRole("administrator", "normal")
                        .antMatchers("/dashboard/**", "/clinic/**",
                                "/insurance/company/**", "/reports/recommendation/**",
                                "/reports/generator/**").hasRole("administrator")
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt());

        return httpSecurity.build();
    }
}
