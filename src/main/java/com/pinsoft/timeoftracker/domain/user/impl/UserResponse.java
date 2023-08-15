package com.pinsoft.timeoftracker.domain.user.impl;

import com.pinsoft.timeoftracker.domain.user.api.UserDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private final String id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final UserRole role;

    public static UserResponse fromDto(UserDto dto) {
        return UserResponse.builder()
                .role(dto.getRole())
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .build();
    }


}
