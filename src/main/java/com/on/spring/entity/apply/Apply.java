package com.on.spring.entity.apply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @AllArgsConstructor @NoArgsConstructor @Builder
public class Apply {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applyId;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private Long companyId;

    @Column(nullable = false)
    private Date date;
}
