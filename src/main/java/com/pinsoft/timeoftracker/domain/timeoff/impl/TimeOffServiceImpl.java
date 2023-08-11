package com.pinsoft.timeoftracker.domain.timeoff.impl;

import com.pinsoft.timeoftracker.domain.timeoff.api.TimeOffDto;
import com.pinsoft.timeoftracker.domain.timeoff.api.TimeOffService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class TimeOffServiceImpl implements TimeOffService {

    private final TimeOffRepository repository;

    @Override
    public TimeOffDto createTimeOff(TimeOffDto dto) {
        TimeOff timeOff = repository.save(toEntity(new TimeOff(),dto));
        return toDto(timeOff);
    }

    @Override
    @Transactional
    public TimeOffDto updateTimeOff(TimeOffDto dto, String id) {

        return repository.findById(id)
                .map(timeOff -> toEntity(timeOff,dto))
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

    public TimeOffDto toDto(TimeOff timeOff){
        return TimeOffDto.builder()
                .startDate(timeOff.getStartDate())
                .endDate(timeOff.getEndDate())
                .description(timeOff.getDescription())
                .id(timeOff.getId())
                .timeOffType(timeOff.getTimeOffType())
                .build();
    }
    public TimeOff toEntity(TimeOff timeOff, TimeOffDto dto){

        timeOff.setTimeOffType(dto.getTimeOffType());
        timeOff.setDescription(dto.getDescription());
        timeOff.setStartDate(dto.getStartDate());
        timeOff.setEndDate(dto.getEndDate());

        return timeOff;
    }








}
