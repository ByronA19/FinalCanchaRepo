package com.project.canchas.config;

import com.project.canchas.model.Usuario;
import com.project.canchas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UsuarioService userService;
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    // authentication
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("javainuse")
//                .password("javainuse").roles("USER");
//    }

    // authorication
    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http.antMatcher("/**").authorizeRequests().anyRequest().hasRole("USER")
//                .and().formLogin().loginPage("/login")
//                .failureUrl("/login?error=1").loginProcessingUrl("/login")
//                .permitAll().and().logout()
//                .logoutSuccessUrl("/login");

        http.cors().and().csrf().disable();

         http
            .authorizeRequests()
                .antMatchers("/assets/**", "/register", "/signup", "/recover-password", "/recoverpasswordpost").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .permitAll()
                .and()
            .logout()
                .permitAll();

    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
    
//    public String getUsername() {
//        SecurityContext context = SecurityContextHolder.getContext();
//        Authentication authentication = context.getAuthentication();
//        if (authentication == null) {
//            return null;
//        }
//        Object principal = authentication.getPrincipal();
//        if (principal instanceof UserDetails) {
//            return "xxx";//((Usuario) principal).getNombre();
//        } else {
//            return "yyy"; //((Usuario)principal).getNombre();
//        }
//    }
//
//    public Usuario getCurrentUser() {
//        Usuario user = userService.findByUsername(getUsername());
//        return user;
//    }
}