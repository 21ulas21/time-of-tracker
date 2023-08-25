package com.pinsoft.timeoftracker.domain.user.api;


import com.pinsoft.timeoftracker.domain.user.impl.UserRole;

import java.util.List;

public interface UserService {

    UserDto getUserById(String id);

    UserDto updateUser(String id, UserDto dto);

    List<UserDto> getUserByManagerRole();

    UserDto changeUserRole(String id, UserRole userRole);

    void deleteUserById(String id);

    UserDto getAuthenticatedUser();

    void changePassword(String oldPassword, String password);

    List<UserDto> getAllUser();
}
