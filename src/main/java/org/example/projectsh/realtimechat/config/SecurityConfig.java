package org.example.projectsh.realtimechat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration



public class SecurityConfig {
    @Bean

    public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder passwordEncoder) {
        UserDetails user1= User.builder()
                .username("user1")
                .password(passwordEncoder.encode("123"))
                .roles("USER")
                .build();
        UserDetails user2=User.builder()
                .username("user2")
                .password(passwordEncoder.encode("456"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1,user2);


    }
    @Bean

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http.authorizeHttpRequests(request->{
           request.requestMatchers("/", "/login", "/error", "/ws/**").permitAll()
                   .anyRequest().authenticated();

       }).csrf((c)->c.disable())
               .formLogin(Customizer.withDefaults());

       return http.build();


    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();

    }
}
