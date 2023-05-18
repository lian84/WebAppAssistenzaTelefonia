package com.example.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/login").permitAll()
            //    .antMatchers("/admin/**").hasRole("ADMIN") // Esempio: Solo gli utenti con ruolo ADMIN possono accedere alle pagine sotto /admin
         //       .antMatchers("/user/**").hasAnyRole("ADMIN", "USER") // Esempio: Gli utenti con ruolo ADMIN o USER possono accedere alle pagine sotto /user
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")   //la pagina da dove si fa login
                .defaultSuccessUrl("/dashboard")  //la pagina dove si viene dirottati dopo la login
                .and()
            .logout()
                .logoutUrl("/logout") //il link dove accedere per uscire dalla sessione
                .logoutSuccessUrl("/login") //il link che viene aperto dopo aver chiuso la sessione
                .and()
            .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();	 //la password deve essere codificata in BCrypt nel database altrimenti non viene riconosciuta
    }
}
