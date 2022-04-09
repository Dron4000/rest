package com.example.rest.security;

import com.example.rest.models.User;
import com.example.rest.services.RoleServices;
import com.example.rest.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private RoleServices roleServices;
    @Autowired
    private UserServices userServices;


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // конфигурация для прохождения аутентификации
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/user")
                .hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/api/admin/**", "/admin/**").hasAuthority("ADMIN")
                .and().httpBasic()
                .and().formLogin().permitAll().successHandler(new SuccessUserHandler()).and()
                .logout().logoutSuccessUrl("/login");

    }



    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);

    }
//    // 1 вариант
//    @Bean(initMethod="init",destroyMethod = "destr")
//    public InitMethodExampleBean initMethodExampleBean1() {
//        return new InitMethodExampleBean();
//    }
//    //2 вариант
//    @Bean
//    public InitMethodExampleBean initMethodExampleBean2() {
//        InitMethodExampleBean initMethodExampleBean = new InitMethodExampleBean();
//        initMethodExampleBean.init();
//        return initMethodExampleBean;
//    }

    @PostConstruct
    private void initMethod() {
        User admin = new User("Andrey", "Andrey", 15, "adnrey200@com"
                , "1111"
                , roleServices.getSetRoles(roleServices.getAllRoles()
                .stream()
                .map(a -> a.getName())
                .collect(Collectors.toSet())));
        userServices.save(admin);

    }


}
