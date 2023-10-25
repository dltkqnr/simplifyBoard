package com.study.board.domain;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
abstract class BaseTimeEntity {

    @Column(name = "create_date_time")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "modified_date_time")
    @LastModifiedDate
    private LocalDateTime modifiedAt;

}
