package com.pinsoft.timeoftracker.domain.timeoff.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TimeOffTypeRequest {
    private final String timeOffType;

    public TimeOffTypeRequest(@JsonProperty("timeOffType") String timeOffType){
        this.timeOffType=timeOffType;
    }
}
