package com.pinsoft.timeoftracker.domain.user.impl;

import com.pinsoft.timeoftracker.library.entity.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = User.TABLE)

public class User extends AbstractEntity {


    public static final String TABLE="t_user";
    private static final String COL_FIRSTS_NAME ="firsts-name";
    private static final String COL_LAST_NAME="last-name";
    private static final String COL_EMAIL="email";
    private static final String COL_PASSWORD="password";
    private static final String COL_STATUS="status";

    @Column(name = COL_FIRSTS_NAME)
    private String firstName;
    @Column(name = COL_LAST_NAME)
    private String lastName;
    @Column(name = COL_EMAIL,nullable = false,unique = true)
    private String email;
    @Column(name = COL_PASSWORD,nullable = false)
    private String password;
    @Column(name = COL_STATUS)
    private boolean status = true;

}
