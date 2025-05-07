package com.blog.domain.board.application.dto;

import java.util.List;

import lombok.Builder;

@Builder
public record PostReadAllResponse(
	List<PostReadResponse> post,
	long pageMax
) {
	public static PostReadAllResponse toResponse(List<PostReadResponse> postDtos, int pageSize) {
		return PostReadAllResponse.builder()
			.post(postDtos)
			.pageMax(pageSize)
			.build();
	}
}
