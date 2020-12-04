package com.on.spring.entity.work;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @AllArgsConstructor @NoArgsConstructor @Builder
public class Work {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String requestId;

    @Column(nullable = false)
    private String targetUserEmail;

    @Column(nullable = false)
    private String workName;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private String workContent;
}
