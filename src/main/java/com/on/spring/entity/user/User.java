package com.on.spring.entity.user;

import com.on.spring.entity.company.Company;
import com.on.spring.entity.glass.Grass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "user")
@Getter @AllArgsConstructor @NoArgsConstructor @Builder
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grass")
    private List<Grass> grasses = new ArrayList<>();

    @Column
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @ManyToOne
    @JoinColumn("company")
    private Company company;

    public User addGlass(Grass grass) {
        grasses.add(grass);
        return this;
    }

    public boolean isOwner() {
        return this.userType.equals(UserType.OWNER);
    }

    public boolean isExecutive() {
        return this.userType.equals(UserType.OWNER) || this.userType.equals(UserType.EXECUTIVE);
    }

    public User switchOwner() {
        this.userType = UserType.OWNER;
        return this;
    }

    public User switchExecutive() {
        this.userType = UserType.EXECUTIVE;
        return this;
    }
}
