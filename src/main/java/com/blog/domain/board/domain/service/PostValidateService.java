package com.blog.domain.board.domain.service;

import com.blog.domain.board.domain.entity.Post;
import com.blog.domain.board.domain.repository.PostRepository;
import com.blog.domain.board.exception.PostAccessDeniedException;
import com.blog.domain.user.domain.entity.User;
import com.blog.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostValidateService {

    public void certificate(Post post, User user) {
        if (!post.getUser().equals(user)) {
            throw new PostAccessDeniedException();
        }
    }
}
