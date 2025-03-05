package com.blog.domain.user.domain.service;

import com.blog.domain.user.domain.entity.User;
import com.blog.domain.user.domain.repository.UserRepository;
import com.blog.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserGetService {
    private final UserRepository userRepository;

    public User find(long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }
}
