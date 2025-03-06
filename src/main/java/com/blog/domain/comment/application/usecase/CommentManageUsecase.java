package com.blog.domain.comment.application.usecase;

import com.blog.domain.board.domain.entity.Post;
import com.blog.domain.board.domain.service.PostGetService;
import com.blog.domain.comment.application.dto.CommentCreateDto;
import com.blog.domain.comment.application.dto.CommentUpdateDto;
import com.blog.domain.comment.domain.entity.Comment;
import com.blog.domain.comment.domain.service.CommentGetService;
import com.blog.domain.comment.domain.service.CommentSaveService;
import com.blog.domain.comment.domain.service.CommentUpdateService;
import com.blog.domain.comment.domain.service.CommentValidateService;
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
    private final CommentGetService commentGetService;
    private final CommentValidateService commentValidateService;
    private final CommentUpdateService commentUpdateService;

    @Transactional
    public void createComment(Long userId,UUID postId, CommentCreateDto dto) {
        User user = userGetService.find(userId);
        Post post = postGetService.find(postId);
        Comment comment = Comment.of(dto.content(), post, user);

        commentSaveService.save(comment);
    }

    @Transactional
    public void updateComment(Long userId, Long commentId, CommentUpdateDto dto) {
        User user = userGetService.find(userId);
        Comment comment = commentGetService.find(commentId);

        commentValidateService.certificate(comment, user);

        commentUpdateService.update(comment,dto);
    }
}
