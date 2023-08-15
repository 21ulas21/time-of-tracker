package com.pinsoft.timeoftracker.domain.user.api;

import com.pinsoft.timeoftracker.domain.user.api.UserDto;

import java.util.List;

public interface UserService {

    UserDto getUserById(String id);

    UserDto updateUser(String id, UserDto dto);

    List<UserDto> getUserByManagerRole();

}
