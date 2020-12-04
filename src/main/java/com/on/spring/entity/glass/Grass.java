package com.on.spring.entity.glass;

import com.on.spring.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity @Getter @AllArgsConstructor @NoArgsConstructor @Builder
public class Grass {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @Column(nullable = false)
    private Date createdDateAt;

    @Column(nullable = false)
    private String information;
}