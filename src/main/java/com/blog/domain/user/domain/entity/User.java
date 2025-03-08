package com.blog.domain.user.domain.entity;

import com.blog.domain.auth.dto.requests.RegisterPostRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
    private String nickname;

    @Column(name = "profile_picture", length = 255)
    private String profilePicture;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    public static User create(RegisterPostRequest request, String encodedPassword) {
        return User.builder()
            .nickname(request.nickname())
            .profilePicture(request.profilePicture())
            .email(request.email())
            .password(encodedPassword)
            .build();
    }
}