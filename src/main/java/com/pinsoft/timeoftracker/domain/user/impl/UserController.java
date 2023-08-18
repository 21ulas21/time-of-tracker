package com.pinsoft.timeoftracker.domain.user.impl;

import com.pinsoft.timeoftracker.domain.user.api.UserDto;
import com.pinsoft.timeoftracker.domain.user.api.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {


    private final UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable(value = "id") String id) {
        var result = service.getUserById(id);
        return ResponseEntity.ok(UserResponse.fromDto(result));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable(value = "id") String id,
                                                   @RequestBody UserRequest request) {
        var result = service.updateUser(id, request.toDto());
        return ResponseEntity.ok(UserResponse.fromDto(result));
    }

    @GetMapping()
    public ResponseEntity<List<UserResponse>> getUserByManagerRole() {

        List<UserResponse> userResponse = service.getUserByManagerRole()
                .stream()
                .map(UserResponse::fromDto)
                .toList();
        return ResponseEntity.ok(userResponse);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponse> updateUserRole(@PathVariable(value = "id") String id, @RequestBody UserRole userRole){

        UserDto user = service.changeUserRole(id, userRole);
        return ResponseEntity.ok(UserResponse.fromDto(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "id")String id){
        service.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

}
