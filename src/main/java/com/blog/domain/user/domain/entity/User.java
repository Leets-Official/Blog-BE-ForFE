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
import lombok.Setter;

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
    @Setter
    private String nickname;

    @Column(length = 20)
    private String name;

    @Column(name = "profile_picture", length = 255)
    @Setter
    private String profilePicture;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    @Setter
    private String password;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(length = 30)
    private String introduction;

    public static User create(RegisterPostRequest request, String encodedPassword) {
        return User.builder()
            .nickname(request.nickname())
            .profilePicture(request.profilePicture())
            .email(request.email())
            .password(encodedPassword)
            .birthDate(LocalDate.parse(request.birthDate()))
            .name(request.name())
            .introduction(request.introduction())
            .build();
    }
}