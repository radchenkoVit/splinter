package com.radchenko.splinter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.radchenko.splinter.entity.role.RoleType.ROLE_ADMIN;
import static com.radchenko.splinter.entity.role.RoleType.ROLE_USER;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public WebSecurityConfig(UserDetailsService userDetailService, BCryptPasswordEncoder passwordEncoder) {
        this.userDetailService = userDetailService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
        http.csrf().disable()
                .authorizeRequests().antMatchers("/h2-console/**").permitAll()
                .and()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/login", "/registration", "/activate/*").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/user**").hasRole(ROLE_ADMIN.val()) //forbid to see add application page except user with Developer Role
                .antMatchers("/**").hasAnyRole(ROLE_ADMIN.val(), ROLE_USER.val()) //forbid to see add application page except user with Developer Role
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/", true)//https://stackoverflow.com/questions/36243352/how-to-set-redirection-after-successful-login
                    .permitAll()
                .and()
                    .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

}
