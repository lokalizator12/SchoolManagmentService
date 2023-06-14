package com.syberry.school.services.impl;

import com.syberry.school.entity.Role;
import com.syberry.school.entity.User;
import com.syberry.school.repositories.RoleRepository;
import com.syberry.school.repositories.UserRepository;
import com.syberry.school.services.AuthorizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Component("userDetailsService")
class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private AuthorizationService authorizationService;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user;
        if (login.equals("admin")) {
            Set<Role> roles = new HashSet<>();
            roles.add(Role.builder().name("ROLE_ADMIN").build());
            try {
                user = User.builder()
                        .email("admin@admina.net")
                        .lastName("admin")
                        .firstName("admin")
                        .login("admin")
                        .avatar(Files.readAllBytes(Paths.get("src/main/resources/static/images/avatars", "avatar-null.jpg")))
                        .password(new BCryptPasswordEncoder().encode("password"))
                        .roles(roles).build();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            roles.add(Role.builder().name("ROLE_TEACHER").build());
            roles.add(Role.builder().name("ROLE_STUDENT").build());
            roleRepository.saveAll(roles);
        } else {
            Optional<User> account = authorizationService.findByLogin(login);
            if (account.isEmpty()) {
                log.error("Profile with login:{}, is not found", login);
                throw new UsernameNotFoundException("Account not found");
            }
            user = account.get();
        }
        log.info("Set authority user: {}", user.getLogin());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role authority : user.getRoles()) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority.getName());
            log.info("SimpleGranted: {}", simpleGrantedAuthority);
            grantedAuthorities.add(simpleGrantedAuthority);
        }
        log.info("Authority user: {} is: {}", user.getLogin(), grantedAuthorities);
        log.info("Security core, with user:{}", user.getLogin());
        if (user.getLogin().equals("admin") && !userRepository.existsByLogin("admin")) {
            userRepository.save(user);
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), grantedAuthorities);
    }
}