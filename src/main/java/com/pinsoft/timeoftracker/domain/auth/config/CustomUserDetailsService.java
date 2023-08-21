package com.pinsoft.timeoftracker.domain.auth.config;

import com.pinsoft.timeoftracker.domain.user.impl.UserRepository;
import com.pinsoft.timeoftracker.domain.user.impl.UserServiceImpl;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println(email);

        var user = repository.findUserByEmail(email).get();
        System.out.println(user.getRole());
        return new CustomUserDetails(user);
    }

}
