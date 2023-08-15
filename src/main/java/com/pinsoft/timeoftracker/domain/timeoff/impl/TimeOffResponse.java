package com.pinsoft.timeoftracker.domain.timeoff.impl;

import com.pinsoft.timeoftracker.domain.timeoff.api.TimeOffDto;
import com.pinsoft.timeoftracker.domain.user.api.UserDto;
import com.pinsoft.timeoftracker.domain.user.impl.User;
import com.pinsoft.timeoftracker.domain.user.impl.UserResponse;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TimeOffResponse {

    private final String id;
    private final String description;
    private final Date startDate;
    private final Date endDate;
    private final TimeOffType timeOffType;
    private final UserResponse employee;

    public static TimeOffResponse fromDto(TimeOffDto dto){
        return TimeOffResponse.builder()
                .employee(UserResponse.fromDto(dto.getEmployeeUser()))
                .timeOffType(dto.getTimeOffType())
                .id(dto.getId())
                .description(dto.getDescription())
                .endDate(dto.getEndDate())
                .startDate(dto.getStartDate())
                .build();
    }


}
