package com.pinsoft.timeoftracker.domain.user.impl;

import com.pinsoft.timeoftracker.domain.user.api.UserDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final boolean status;


    public UserDto toDto() {
        return UserDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .status(status)
                .build();
    }


}
