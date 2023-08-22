package com.pinsoft.timeoftracker.domain.timeoff.impl;


import com.pinsoft.timeoftracker.domain.timeoff.api.TimeOffDto;
import com.pinsoft.timeoftracker.domain.user.api.UserDto;
import com.pinsoft.timeoftracker.domain.user.impl.UserRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TimeOffRequest {

    private final String description;
    @NotBlank(message = "Başlangıç tarihini giriniz")
    private final Date startDate;
    @NotBlank(message = "Bitiş tarihini giriniz")
    private final Date endDate;

    private final String employeeId;

    private final String managerId;

    public TimeOffDto toDto() {
        return TimeOffDto.builder()
                .employeeUser(UserDto.builder().id(employeeId).build())
                .managerUser(UserDto.builder().id(managerId).build())
                .timeOffType(TimeOffType.PENDING)
                .description(description)
                .endDate(endDate)
                .startDate(startDate)
                .build();
    }


}

