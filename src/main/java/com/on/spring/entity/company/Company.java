package com.on.spring.entity.company;

import com.on.spring.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor @NoArgsConstructor @Builder
public class Company {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false, unique = true)
    private String ownerEmail;

    @OneToMany(mappedBy = "company", targetEntity = User.class)
    private final List<User> users = new ArrayList<>();

    @Column(nullable = false)
    private String companyAddress;

    @Column(nullable = false)
    private Long likes;

    public Company addUser(User user) {
        users.add(user);
        return this;
    }

    public Company addLike() {
        this.likes++;
        return this;
    }
}
