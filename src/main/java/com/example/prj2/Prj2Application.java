package com.example.prj2;

import com.example.prj2.entity.User;
import com.example.prj2.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Prj2Application {

	public static void main(String[] args) {
		SpringApplication.run(Prj2Application.class, args);
	}
//    @Bean
//    CommandLineRunner init(UserRepository userRepository) {
//        return args -> {
//            if (userRepository.count() == 0) {
//                User user = new User();
//                user.setUsername("admin");
//                user.setPassword("admin");
//                user.setRole("admin");
//                userRepository.save(user);
//                System.out.println("add admin succesfilly");
//
//            }
//
//        };
//    }
}
