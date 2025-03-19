package com.blog.domain.board.domain.entity;

import com.blog.domain.board.application.dto.ContentDto;
import com.blog.domain.board.domain.constant.ContentType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "contents")
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Long contentOrder;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private String content;

    private ContentType contentType;

    public static Content fromDto(ContentDto dto, Post post) {

        return Content.builder()
                .contentOrder(dto.contentOrder())
                .content(dto.content())
                .contentType(dto.contentType())
                .post(post)
                .build();
    }
}
