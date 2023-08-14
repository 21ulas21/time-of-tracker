package com.pinsoft.timeoftracker.domain.user.api;

import com.pinsoft.timeoftracker.domain.user.api.UserDto;

public interface UserService {

    UserDto getUserById(String id);
    UserDto updateUser(String id, UserDto dto);

}
