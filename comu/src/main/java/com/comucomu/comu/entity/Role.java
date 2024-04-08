package com.comucomu.comu.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Role {

    // 역할 id
    @Id
    @Column(name = "role_id")
    private String id;

    // 역할 명
    @Column(nullable = false)
    private String roleName;

    @OneToOne(mappedBy = "role")
    private User user;


}
