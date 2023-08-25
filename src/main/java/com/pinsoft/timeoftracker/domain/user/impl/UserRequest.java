package com.pinsoft.timeoftracker.domain.user.impl;

import com.pinsoft.timeoftracker.domain.user.api.UserDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {
    @NotBlank(message = "Enter a name")
    private final String firstName;
    @NotBlank(message = "Enter surname")
    private final String lastName;
    @NotBlank(message = "Email is missing or incorrectly entered")
    private final String email;
    @NotBlank(message = "Enter a password")
    private final String password;


    public UserDto toDto() {
        return UserDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .build();
    }


}
