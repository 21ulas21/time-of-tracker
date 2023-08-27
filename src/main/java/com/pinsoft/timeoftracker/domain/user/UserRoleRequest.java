package com.pinsoft.timeoftracker.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data

public class UserRoleRequest {

    private final String userRole;

    public UserRoleRequest(@JsonProperty("userRole") String userRole){
        this.userRole = userRole;
    }
}
