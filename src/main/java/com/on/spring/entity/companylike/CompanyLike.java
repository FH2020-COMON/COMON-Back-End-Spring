package com.on.spring.entity.companylike;

import com.on.spring.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "company_likes")
@Getter @AllArgsConstructor @NoArgsConstructor
public class CompanyLike {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long companyId;

    @Column(nullable = false)
    private String userId;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;
}
