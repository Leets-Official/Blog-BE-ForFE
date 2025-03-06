package com.blog.domain.board.application.usecase;

import com.blog.domain.board.application.dto.PostCreateDto;
import com.blog.domain.board.application.dto.PostReadAllResponse;
import com.blog.domain.board.application.dto.PostReadResponse;
import com.blog.domain.board.application.dto.PostUpdateDto;
import com.blog.domain.board.domain.entity.Post;
import com.blog.domain.board.domain.service.PostDeleteService;
import com.blog.domain.board.domain.service.PostGetService;
import com.blog.domain.board.domain.service.PostSaveService;
import com.blog.domain.board.domain.service.PostUpdateService;
import com.blog.domain.board.domain.service.PostValidateService;
import com.blog.domain.comment.domain.entity.Comment;
import com.blog.domain.comment.domain.service.CommentGetService;
import com.blog.domain.user.domain.entity.User;
import com.blog.domain.user.domain.service.UserGetService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostManageUsecase {
    private final UserGetService userGetService;
    private final PostSaveService postSaveService;
    private final PostGetService postGetService;
    private final PostUpdateService postUpdateService;
    private final PostValidateService postValidateService;
    private final PostDeleteService postDeleteService;
    private final CommentGetService commentGetService;

    @Transactional
    public void createPost(Long userId, PostCreateDto dto) {
        User user = userGetService.find(userId);
        Post post = Post.CreatePost(dto.title(), dto.content(), dto.image(), user);

        postSaveService.save(post);
    }

    @Transactional(readOnly = true)
    public PostReadResponse readPost(Long userId , UUID postId) {
        User user = userGetService.find(userId);
        Post post = postGetService.find(postId);

        List<Comment> comments = commentGetService.findALlByPost(post);

        return PostReadResponse.toResponse(post, user, comments);
    }

    @Transactional(readOnly = true)
    public List<PostReadAllResponse> readAllPost(Long userId, int size, int page) {
        User user = userGetService.find(userId);
        List<Post> posts = postGetService.findAll(size, page);

        return posts.stream()
                .map(post -> PostReadAllResponse.toResponse(post, user)).toList();
    }

    @Transactional
    public void updatePost(Long userId, UUID postId, PostUpdateDto dto) {
        User user = userGetService.find(userId);
        Post post = postGetService.find(postId);

        postValidateService.certificate(post,user);

        postUpdateService.update(post,dto);
    }

    @Transactional
    public void deletePost(Long userId, UUID postId) {
        User user = userGetService.find(userId);
        Post post = postGetService.find(postId);

        postValidateService.certificate(post,user);

        postDeleteService.delete(post);
    }
}
