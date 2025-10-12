package com.blog.domain.board.application.dto;

import java.util.List;

import lombok.Builder;

@Builder
public record PostReadAllResponse(
		List<PostSummaryResponse> posts,
		long pageMax
) {
	public static PostReadAllResponse toResponse(List<PostSummaryResponse> postDtos, int pageSize) {
		return PostReadAllResponse.builder()
				.posts(postDtos)
				.pageMax(pageSize)
				.build();
	}
}

