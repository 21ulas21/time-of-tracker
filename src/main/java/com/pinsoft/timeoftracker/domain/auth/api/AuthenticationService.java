package com.pinsoft.timeoftracker.domain.auth.api;

import com.pinsoft.timeoftracker.domain.auth.impl.AuthenticationRequest;
import com.pinsoft.timeoftracker.domain.auth.impl.AuthenticationResponse;
import com.pinsoft.timeoftracker.domain.user.api.UserDto;
import com.pinsoft.timeoftracker.domain.user.impl.UserRequest;

public interface AuthenticationService {
    AuthenticationResponse login(AuthenticationRequest request);
    UserDto register(UserDto dto);

}
