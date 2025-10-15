package com.blog.domain.board.domain.entity;

import com.blog.domain.board.application.dto.ContentDto;
import com.blog.domain.comment.domain.entity.Comment;
import com.blog.domain.user.domain.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @NotBlank
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    // ❗️ Post 저장 시 Content와 Comment도 함께 영속성 관리가 되도록 cascade 옵션을 유지합니다.
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Content> contents = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    // ❗️❗️ 이 부분이 트랜잭션 문제 해결의 핵심입니다.
    // Post를 생성할 때 Content 리스트도 함께 받아 엔티티를 생성하도록 수정합니다.
    public static Post CreatePost(String title, User user, List<ContentDto> contentDtos) {
        Post post = Post.builder()
            .title(title)
            .user(user)
            .build();

        List<Content> contents = contentDtos.stream()
            .map(dto -> Content.fromDto(dto, post))
            .collect(Collectors.toList());
        post.setContents(contents); // 연관관계 설정

        return post;
    }

    public void update(String title) {
        this.title = title;
    }

    // 연관관계 편의 메서드
    private void setContents(List<Content> contents) {
        this.contents = contents;
    }
}

