package com.pinsoft.timeoftracker.domain.timeoff.impl;

import com.pinsoft.timeoftracker.domain.timeoff.api.TimeOffDto;
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

    public static TimeOffResponse fromDto(TimeOffDto dto){
        return TimeOffResponse.builder()
                .timeOffType(dto.getTimeOffType())
                .id(dto.getId())
                .description(dto.getDescription())
                .endDate(dto.getEndDate())
                .startDate(dto.getStartDate())
                .build();
    }


}
