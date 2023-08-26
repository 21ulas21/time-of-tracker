package com.pinsoft.timeoftracker.domain.timeoff.impl;

import com.pinsoft.timeoftracker.domain.timeoff.api.TimeOffDto;
import com.pinsoft.timeoftracker.domain.timeoff.api.TimeOffService;
import com.pinsoft.timeoftracker.domain.user.impl.User;
import com.pinsoft.timeoftracker.domain.user.impl.UserServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TimeOffServiceImpl implements TimeOffService {

    private final TimeOffRepository repository;
    private final UserServiceImpl userService;

    @Override
    public TimeOffDto createTimeOff(TimeOffDto dto) {
        TimeOff timeOff = toEntity(new TimeOff(), dto);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserEntityByEmail(authentication.getName());
        timeOff.setEmployee(user);
        return toDto(repository.save(timeOff));
    }

    @Override
    @Transactional
    public TimeOffDto updateTimeOff(TimeOffDto dto, String id) {

        return repository.findById(id)
                .map(timeOff -> toEntity(timeOff, dto))
                .map(repository::save)
                .map(this::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public void deleteTimeOff(String id) {

        repository.delete(repository.findById(id).orElseThrow(EntityNotFoundException::new));

    }

    @Override
    public TimeOffDto getTimeOffDto(String id) {

        return repository.findById(id).map(this::toDto).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<TimeOffDto> getAllTimeOff() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public TimeOffDto updateTimeOffType(String id, TimeOffType timeOffType) {
        TimeOff timeOff = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        timeOff.setTimeOffType(timeOffType);
        return toDto(repository.save(timeOff));
    }

    @Override
    public List<TimeOffDto> getTimeOffForManager() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserEntityByEmail(authentication.getName());
      return repository.findTimeOffByManagerId(user.getId()).stream().map(this::toDto).toList();

    }

    public TimeOffDto toDto(TimeOff timeOff) {
        return TimeOffDto.builder()
                .startDate(timeOff.getStartDate())
                .endDate(timeOff.getEndDate())
                .description(timeOff.getDescription())
                .id(timeOff.getId())
                .timeOffType(timeOff.getTimeOffType())
                .managerUser(userService.toDto(timeOff.getManager()))
                .employeeUser(userService.toDto(timeOff.getEmployee()))
                .build();
    }

    public TimeOff toEntity(TimeOff timeOff, TimeOffDto dto) {
        timeOff.setManager(userService.getUserEntityById(dto.getManagerUser().getId()));
        timeOff.setDescription(dto.getDescription());
        timeOff.setStartDate(dto.getStartDate());
        timeOff.setEndDate(dto.getEndDate());
        timeOff.setTimeOffType(dto.getTimeOffType()==null ? TimeOffType.PENDING:dto.getTimeOffType());

        return timeOff;
    }



}
