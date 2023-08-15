package com.pinsoft.timeoftracker.domain.user.api;

import com.pinsoft.timeoftracker.domain.user.impl.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private final String id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final boolean status;
    private final UserDto employee;
    private final UserDto manager;
    private final UserRole role;

}
