package com.pinsoft.timeoftracker.domain.timeoff.api;

import com.pinsoft.timeoftracker.domain.timeoff.impl.TimeOffType;
import com.pinsoft.timeoftracker.domain.user.api.UserDto;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TimeOffDto {
    private final String id;
    private final String description;
    private final Date startDate;
    private final Date endDate;
    private final TimeOffType timeOffType;
    private final UserDto employeeUser;
    private final UserDto managerUser;
}
