package com.pinsoft.timeoftracker.domain.timeoff.impl;

import com.pinsoft.timeoftracker.domain.user.impl.User;
import com.pinsoft.timeoftracker.library.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = TimeOff.TABLE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeOff extends AbstractEntity {

    public static final String TABLE = "time-off";
    private static final String COL_START_DATE = "start-date";
    private static final String COL_DESCRIPTION = "description";
    private static final String COL_END_DATE = "end-date";
    private static final String COL_TIME_OFF_TYPE = "time-off-type";
    private static final String COL_EMPLOYEE_ID = "employee-id";
    private static final String COL_MANAGER_ID = "manager-id";


    @Column(name = COL_DESCRIPTION, length = 750)
    private String description;
    @Column(name = COL_START_DATE)
    private Date startDate;
    @Column(name = COL_END_DATE)
    private Date endDate;
    @Enumerated(EnumType.STRING)
    @Column(name = COL_TIME_OFF_TYPE)
    private TimeOffType timeOffType;
    @JoinColumn(name = COL_EMPLOYEE_ID)
    @OneToOne
    private User employee;
    @JoinColumn(name = COL_MANAGER_ID)
    @OneToOne
    private User manager;


}
