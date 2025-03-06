package com.blog.domain.comment.application.usecase;

import com.blog.domain.board.domain.entity.Post;
import com.blog.domain.board.domain.service.PostGetService;
import com.blog.domain.comment.application.dto.CommentCreateDto;
import com.blog.domain.comment.domain.entity.Comment;
import com.blog.domain.comment.domain.service.CommentSaveService;
import com.blog.domain.user.domain.entity.User;
import com.blog.domain.user.domain.service.UserGetService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentManageUsecase {
    private final CommentSaveService commentSaveService;
    private final UserGetService userGetService;
    private final PostGetService postGetService;

    @Transactional
    public void createComment(Long userId,UUID postId, CommentCreateDto dto) {
        User user = userGetService.find(userId);
        Post post = postGetService.find(postId);
        Comment comment = Comment.of(dto.content(), post, user);

        commentSaveService.save(comment);
    }
}
