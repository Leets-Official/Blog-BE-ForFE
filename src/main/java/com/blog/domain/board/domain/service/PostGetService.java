package com.blog.domain.board.domain.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.domain.board.domain.entity.Post;
import com.blog.domain.board.domain.repository.PostRepository;
import com.blog.domain.board.exception.PostNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostGetService {
	private final PostRepository postRepository;

	public Post find(UUID postId) {
		return postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
	}

	public Page<Post> findAll(int size, int page) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
		return postRepository.findAll(pageable);
	}
}
