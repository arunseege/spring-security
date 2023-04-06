package com.javabrains.springsecurity.springbootsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
        UserDetails admin = User.withUsername("arun")
                .password(passwordEncoder.encode("arun"))
                .roles("ADMIN")
                .build();

        UserDetails user = User.withUsername("kiran")
                .password(passwordEncoder.encode("kiran"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin,user);
    }

        @Bean
        public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/products/welcome")
                .permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/products/**")
                .authenticated()
                .and().formLogin()
                .and().build();

    }

}
