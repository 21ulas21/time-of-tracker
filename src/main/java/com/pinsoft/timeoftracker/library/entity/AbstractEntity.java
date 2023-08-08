package com.pinsoft.timeoftracker.library.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(of = "id")
public abstract class AbstractEntity {

    @Id
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "UUID")
    @Column(nullable = false, updatable = false)
    private String id;

    @Column(name = "created_date")
    @CreatedDate
    private LocalDateTime localDateTime;

    @LastModifiedDate
    @Column(name = "modified")
    private LocalDateTime modified;





}
