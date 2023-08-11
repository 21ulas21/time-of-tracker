package com.pinsoft.timeoftracker.domain.timeoff.api;

import com.pinsoft.timeoftracker.domain.timeoff.impl.TimeOffType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TimeOffDto {
    public String id;
    public String description;
    public Date startDate;
    public Date endDate;
    public TimeOffType timeOffType;
}
