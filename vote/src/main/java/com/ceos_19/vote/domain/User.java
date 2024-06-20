package com.ceos_19.vote.domain;

import com.ceos_19.vote.common.enumSet.LoginType;
import com.ceos_19.vote.common.enumSet.UserRoleEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true, length = 20)       // 로그인 id
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Part part;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;


    @Column(nullable = true)
    @Enumerated(value = EnumType.STRING)
    private LoginType loginType;

    @Column(nullable = true)
    @Enumerated(value = EnumType.STRING)
    private Team team;

    @Builder
    public User(LoginType socialType, String socialId) {
        this.loginType = socialType;
        this.username = socialId;
    }
    public static User of(LoginType loginType, String username, String password, UserRoleEnum role, Part part, Team team, String email, String name) {
        User user = new User(loginType, username); // 일반 로그인 타입으로 사용자 생성
        user.setPassword(password); // 패스워드 설정
        user.setRole(role); // 역할 설정
        user.setTeam(team);
        user.setPart(part);
        user.setEmail(email);
        user.setName(name);
        return user; // 사용자 반환
    }
}
