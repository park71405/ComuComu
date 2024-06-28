package com.comucomu.comu.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="user")
public class User implements UserDetails {

    // 아이디
    @Id
    @Column(name = "user_id")
    private String id;

    // 비밀번호
    @Column(nullable = false)
    private String password;

    // 닉네임
    @Column(nullable = false)
    private String nickname;

    // 이메일
    @Column(nullable = false)
    private String email;

    // 역할 id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Board> boardList = new ArrayList<>();

    @Builder
    public User(String id, String password, String nickname, String email, Role role) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.role = role;
    }

    // 권한 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("user"));
    }

    // 사용자 id 반환(고유 값)
    @Override
    public String getUsername(){
        return id;
    }

    // 사용자 pw 반환
    @Override
    public String getPassword(){
        return password;
    }

    // 계정 만료여부 반환
    @Override
    public boolean isAccountNonExpired(){
        // 만료여부 확인 로직 추가 필요...

        return true;  //일단 무조건 만료되지 않도록 설정...
    }

    // 계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked(){
        // 계정 잠금 확인 로직 추가 필요....

        return true;    //일단 무조건 풀려있도록 설정...
    }

    // 패스워드 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired(){
        // 패스워드 만료 확인 로직

        return true;    //일단 무조건 만료되지 않도록 설정...
    }

    // 계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled(){
        // 계정 사용 여부 반환 로직

        return true;    //일단 무조건 사용가능하도록 설정...
    }

}
