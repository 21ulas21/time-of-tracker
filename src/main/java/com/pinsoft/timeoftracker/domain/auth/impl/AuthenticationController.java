package com.pinsoft.timeoftracker.domain.auth.impl;

import com.pinsoft.timeoftracker.domain.auth.api.AuthenticationService;
import com.pinsoft.timeoftracker.domain.user.impl.UserRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Slf4j
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        log.info("controller");
        return ResponseEntity.ok(service.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequest request){
        service.register(request.toDto());
        return ResponseEntity.ok("Account Created");
    }


}
