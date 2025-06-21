package com.kelsyfrank.learning;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
@EntityScan(basePackages = "com.kelsyfrank.learning.model")
public class LearningPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningPlatformApplication.class, args);
    }

    // JDBC Connection info logger bean - helps with troubleshooting
    @Bean
    @Profile("!test")
    public CommandLineRunner logConnectionInfo(DataSource dataSource)
    {
        return args -> {
            try (Connection connection = dataSource.getConnection()) {
                System.out.println("Connected to DB: " + connection.getMetaData().getURL());
            } catch (SQLException e) {
                System.err.println("Failed to connect to DB: " + e.getMessage());
            }
        };
    }
}