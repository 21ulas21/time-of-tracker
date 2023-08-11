package com.pinsoft.timeoftracker.domain.timeoff.impl;

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

    public static final String TABLE="time-off";
    private static final String COL_START_DATE="start-date";
    private static final String COL_DESCRIPTION="description";
    private static final String COL_END_DATE="end-date";
    private static final String COL_TIME_OFF_TYPE="time-off-type";



    @Column(name = COL_DESCRIPTION)
    private String description;
    @Column(name = COL_START_DATE)
    private Date startDate;
    @Column(name = COL_END_DATE)
    private Date endDate;
    @Enumerated(EnumType.STRING)
    @Column(name = COL_TIME_OFF_TYPE)
    private TimeOffType timeOffType;








}
