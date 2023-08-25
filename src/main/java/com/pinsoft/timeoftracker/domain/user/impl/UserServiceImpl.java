package com.pinsoft.timeoftracker.domain.user.impl;

import com.pinsoft.timeoftracker.domain.user.api.UserDto;
import com.pinsoft.timeoftracker.domain.user.api.UserService;
import com.pinsoft.timeoftracker.library.exception.WrongPasswordException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;


    public UserDto createUser(User user) {

        if (repository.existsUserByEmail(user.getEmail())){
            throw new EntityExistsException("Email is already in user");
        }
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
        User user = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
       return toDto(repository.save(user));
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

    @Override
    public UserDto getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = getUserEntityByEmail(authentication.getName());
        return toDto(user);
    }

    public User getUserEntityById(String id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    @Override
    public void changePassword(String oldPassword, String password){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = getUserEntityByEmail(authentication.getName());

        if (passwordEncoder.matches(oldPassword, user.getPassword())){

            user.setPassword(passwordEncoder.encode(password));
           createUser(user);
        }else {
            throw new WrongPasswordException("Eski şifreniz yanlış");
        }

    }

    @Override
    public List<UserDto> getAllUser() {
        return repository.findAll().stream().map(this::toDto).toList();
    }


    public User toEntity(User user, UserDto dto) {
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setStatus(dto.getStatus());
        return user;
    }

    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .role(user.getRole())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .status(user.getStatus())
                .build();
    }

}
