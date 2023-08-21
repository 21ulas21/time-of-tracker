package com.pinsoft.timeoftracker.domain.auth.impl;

import com.pinsoft.timeoftracker.domain.user.impl.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {

    private final String token;
    private final UserRole role;
    private final boolean status;

}
