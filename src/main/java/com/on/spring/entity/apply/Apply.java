package com.on.spring.entity.apply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @AllArgsConstructor @NoArgsConstructor @Builder
public class Apply {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applyId;

    @Column(nullable = false)
    private String applyName;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private Long companyId;

    @Column(nullable = false)
    private String hashTag;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private int imageNum;

    public Apply addImageNum(int imageNum) {
        this.imageNum = imageNum;
        return this;
    }
}
