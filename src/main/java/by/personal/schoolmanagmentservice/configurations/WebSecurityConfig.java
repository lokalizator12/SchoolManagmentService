package by.personal.schoolmanagmentservice.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static by.personal.schoolmanagmentservice.utils.ApplicationConstants.*;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig {
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(WHITELIST).permitAll()
                .antMatchers("/news/create", "/users/students/").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")
                .antMatchers(USERS_ACCESS_LIST).hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER", "ROLE_STUDENT")
                .antMatchers(ADMIN_ACCESS_LIST).hasAuthority("ROLE_ADMIN")
                .antMatchers("/registration", "/login").not().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("login")
                .passwordParameter("password")
                .successForwardUrl("/home")
                .defaultSuccessUrl("/home")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and()
                .httpBasic();
        return http.build();
    }
}
