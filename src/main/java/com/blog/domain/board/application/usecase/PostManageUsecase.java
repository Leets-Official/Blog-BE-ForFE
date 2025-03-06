package com.blog.domain.board.application.usecase;

import com.blog.domain.board.application.dto.PostCreateDto;
import com.blog.domain.board.domain.entity.Post;
import com.blog.domain.board.domain.service.PostSaveService;
import com.blog.domain.user.domain.entity.User;
import com.blog.domain.user.domain.service.UserGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostManageUsecase {
    private final UserGetService userGetService;
    private final PostSaveService postSaveService;

    @Transactional
    public void createPost(Long userId, PostCreateDto dto) {
        User user = userGetService.find(userId);
        Post post = Post.CreatePost(dto.title(), dto.content(), dto.image(), user);

        postSaveService.save(post);
    }
}
