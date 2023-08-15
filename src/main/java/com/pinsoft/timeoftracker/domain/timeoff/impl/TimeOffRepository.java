package com.pinsoft.timeoftracker.domain.timeoff.impl;

import com.pinsoft.timeoftracker.domain.user.impl.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeOffRepository extends JpaRepository<TimeOff, String> {

    List<TimeOff> findTimeOffByManager(User user);
}
