package com.pinsoft.timeoftracker.domain.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PasswordChangeRequest {
    @NotBlank(message = "Enter your old password")
    private final String oldPassword;
    @NotBlank(message = "Enter your new password")
    private final String newPassword;
}
