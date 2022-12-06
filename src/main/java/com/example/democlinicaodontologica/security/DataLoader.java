package com.example.democlinicaodontologica.security;

import com.example.democlinicaodontologica.model.AppUser;
import com.example.democlinicaodontologica.model.AppUserRoles;
import com.example.democlinicaodontologica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("password");
        String password2 = passwordEncoder.encode("password2");

        userRepository.save(new AppUser("user1", "username1", "username1@gmail.com", password, AppUserRoles.ADMIN));
        userRepository.save(new AppUser("user2", "username2", "username2@gmail.com", password2, AppUserRoles.USER));
    }
}
