package com.example.identity_service.configuration;

import com.example.identity_service.constant.PredefinedRole;
import com.example.identity_service.entiy.User;
import com.example.identity_service.entiy.Role;
import com.example.identity_service.repository.RoleRepository;
import com.example.identity_service.repository.UserRepostitory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
@Slf4j
public class ApplicationInitConfig {
    @NonFinal
    static final String ADMIN_USER_NAME = "admin";

    @NonFinal
    static final String ADMIN_PASSWORD = "admin";

    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepostitory userRepostitory,
                                        RoleRepository roleRepository) {
        return args -> {
            if(userRepostitory.findByUsername(ADMIN_USER_NAME).isEmpty()) {

                roleRepository.save(Role.builder()
                        .name(PredefinedRole.USER_ROLE)
                                .description("User role")
                        .build());
                Role adminRole = roleRepository.save(Role.builder()
                                .name (PredefinedRole.ADMIN_ROLE)
                                .description("Admin role")
                        .build());
                var roles = new HashSet<Role>();
                roles.add(adminRole);

                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles(roles)
                        .build();

                userRepostitory.save(user);
                log.warn("admin has been created with default password: admin, please change it");
            }
        };
    }
}
