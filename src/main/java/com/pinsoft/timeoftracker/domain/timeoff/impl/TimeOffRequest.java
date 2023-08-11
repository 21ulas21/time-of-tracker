package com.pinsoft.timeoftracker.domain.timeoff.impl;


import com.pinsoft.timeoftracker.domain.timeoff.api.TimeOffDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TimeOffRequest{

    public String description;
    @NotBlank(message = "Başlangıç tarihini giriniz")
    public Date startDate;
    @NotBlank(message = "Bitiş tarihini giriniz")
    public Date endDate;
    public TimeOffType timeOffType;

    public TimeOffDto toDto(){
        return TimeOffDto.builder()
                .timeOffType(timeOffType)
                .description(description)
                .endDate(endDate)
                .startDate(startDate)
                .build();

    }



}

