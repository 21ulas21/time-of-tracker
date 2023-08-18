package com.pinsoft.timeoftracker.domain.timeoff.impl;

import com.pinsoft.timeoftracker.domain.timeoff.api.TimeOffDto;
import com.pinsoft.timeoftracker.domain.timeoff.api.TimeOffService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-off")
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")

public class TimeOffController {

    private final TimeOffService service;

    @PostMapping()
    public ResponseEntity<TimeOffResponse> createTimeOff(@RequestBody TimeOffRequest request) {
        var timeOffDto = service.createTimeOff(request.toDto());
        return ResponseEntity.ok(TimeOffResponse.fromDto(timeOffDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimeOffResponse> updateTimeOff(@RequestBody TimeOffRequest request,
                                                         @PathVariable(value = "id") String id) {
        var timeOffDto = service.updateTimeOff(request.toDto(), id);
        return ResponseEntity.ok(TimeOffResponse.fromDto(timeOffDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimeOff(@PathVariable String id) {
        service.deleteTimeOff(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeOffResponse> getTimeOff(@PathVariable String id) {
        var timeOffDto = service.getTimeOffDto(id);
        return ResponseEntity.ok(TimeOffResponse.fromDto(timeOffDto));
    }

    @GetMapping()
    public ResponseEntity<List<TimeOffResponse>> getAllTimeOff() {
        List<TimeOffResponse> timeOffResponses = service
                .getAllTimeOff()
                .stream()
                .map(TimeOffResponse::fromDto)
                .toList();
        return ResponseEntity.ok(timeOffResponses);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TimeOffResponse> updateTimeOffType(@PathVariable(value = "id") String id,
                                                             @RequestBody TimeOffType timeOffType) {
        TimeOffDto timeOff = service.updateTimeOffType(id, timeOffType);
        return ResponseEntity.ok(TimeOffResponse.fromDto(timeOff));
    }

    @GetMapping("/for-managers")
    public ResponseEntity<List<TimeOffResponse>> getTimeOffForManager(){
        List<TimeOffResponse> timeOffResponses = service.getTimeOffForManager()
                .stream()
                .map(TimeOffResponse::fromDto)
                .toList();
        return ResponseEntity.ok(timeOffResponses);
    }
}
