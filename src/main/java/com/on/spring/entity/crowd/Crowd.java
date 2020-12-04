package com.on.spring.entity.crowd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class Crowd {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String hashTag;

    @Column(nullable = false)
    private String crowdName;

    @Column(nullable = false)
    private int destinationAmount;

    @Column(nullable = false)
    private int nowAmount;

    @Column(nullable = false)
    private int imageNum;
}
