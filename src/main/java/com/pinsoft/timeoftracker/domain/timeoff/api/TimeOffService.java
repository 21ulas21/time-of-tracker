package com.pinsoft.timeoftracker.domain.timeoff.api;

import com.pinsoft.timeoftracker.domain.timeoff.impl.TimeOffType;

import java.util.List;

public interface TimeOffService {
    TimeOffDto createTimeOff(TimeOffDto toDto);

    TimeOffDto updateTimeOff(TimeOffDto toDto, String id);

    void deleteTimeOff(String id);

    TimeOffDto getTimeOffDto(String id);

    List<TimeOffDto> getAllTimeOff();

    TimeOffDto updateTimeOffType(String id, TimeOffType timeOffType);

    List<TimeOffDto> getTimeOffForManager();
}
