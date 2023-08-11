package com.pinsoft.timeoftracker.domain.timeoff.impl;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeOffRepository extends JpaRepository<TimeOff, String> {
}
