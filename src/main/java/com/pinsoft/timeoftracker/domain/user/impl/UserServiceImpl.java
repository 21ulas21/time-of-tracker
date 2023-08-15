package com.pinsoft.timeoftracker.domain.user.impl;

import com.pinsoft.timeoftracker.domain.user.api.UserDto;
import com.pinsoft.timeoftracker.domain.user.api.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;


    public UserDto createUser(User user) {
        return toDto(repository.save(user));
    }

    @Override
    public UserDto getUserById(String id) {
        User user = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return toDto(user);
    }

    public UserDto getUserByEmail(String email) {
        User user = repository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
        return toDto(user);
    }
    public User getUserEntityByEmail(String email){
        return repository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
    }


    @Override
    public UserDto updateUser(String id, UserDto userDto) {
        return repository.findById(id)
                .map(user -> toEntity(user, userDto))
                .map(repository::save)
                .map(this::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<UserDto> getUserByManagerRole() {
        return repository.findByRole(UserRole.MANAGER)
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public UserDto changeUserRole(String id, UserRole userRole) {

        User user = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        user.setRole(userRole);
        return toDto(repository.save(user));
    }

    @Override
    public void deleteUserById(String id) {
        User user = repository.findById(id).orElseThrow(EntityNotFoundException::new);
            repository.delete(user);
    }

    public User getUserEntityById(String id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


    public User toEntity(User user, UserDto dto) {
        user.setRole(dto.getRole());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setStatus(true);
        return user;
    }

    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .role(user.getRole())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .status(user.isStatus())
                .build();
    }

}
