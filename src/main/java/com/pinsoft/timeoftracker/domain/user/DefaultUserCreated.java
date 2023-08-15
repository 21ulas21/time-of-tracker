package com.pinsoft.timeoftracker.domain.user;

import com.pinsoft.timeoftracker.domain.user.impl.User;
import com.pinsoft.timeoftracker.domain.user.impl.UserRepository;
import com.pinsoft.timeoftracker.domain.user.impl.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultUserCreated {

    @Value("${default.user.email}")
    private String email;
    @Value("${default.user.password}")
    private String password;

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @EventListener
    public void onApplicationStartup(ApplicationReadyEvent readyEvent){

        if(userRepository.findByEmail(email).isEmpty()){
            User user = new User();
            user.setEmail(email);
            user.setPassword(encoder.encode(password));
            user.setStatus(true);
            user.setRole(UserRole.ADMIN);
            userRepository.save(user);

        }


    }



}
