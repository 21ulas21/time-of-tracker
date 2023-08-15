package com.pinsoft.timeoftracker.domain.auth.impl;

import com.pinsoft.timeoftracker.domain.auth.api.AuthenticationService;
import com.pinsoft.timeoftracker.domain.auth.jwt.JwtService;
import com.pinsoft.timeoftracker.domain.user.api.UserDto;
import com.pinsoft.timeoftracker.domain.user.impl.User;
import com.pinsoft.timeoftracker.domain.user.impl.UserRequest;
import com.pinsoft.timeoftracker.domain.user.impl.UserRole;
import com.pinsoft.timeoftracker.domain.user.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        var user = userService.getUserByEmail(request.getEmail());
        var token = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .status(user.isStatus())
                .token(token)
                .build();
    }

    @Override
    public UserDto register(UserRequest request) {
        User user =  userService.toEntity(new User(), request.toDto());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.EMPLOYEE);
        return userService.createUser(user);
    }

}
