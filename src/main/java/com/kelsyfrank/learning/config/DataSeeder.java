package com.kelsyfrank.learning.config;

import com.kelsyfrank.learning.model.User;
import com.kelsyfrank.learning.model.User.Role;
import com.kelsyfrank.learning.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Configuration;;

@Configuration
public class DataSeeder {
    
    @Bean
    @Profile("!test")
    CommandLineRunner seedUsers(UserRepository userRepo) {
        return args -> {
            System.out.println("DB Seeding Active...");

            if (userRepo.count() == 0) {
                userRepo.save(User.builder()
                        .fullName("Kelsy Frank")
                        .email("kelsy@example.com")
                        .password("test123")
                        .role(Role.ADMIN)
                        .build());
                System.out.println("User seeded.");
            } else {
                System.out.println("Users already exist, skipping seed.");
            }
        };
    }
}
