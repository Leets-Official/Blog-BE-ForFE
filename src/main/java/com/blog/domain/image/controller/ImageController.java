package com.blog.domain.image.controller;

import com.blog.domain.image.service.ImageService;
import com.blog.global.common.auth.annotations.UseGuards;
import com.blog.global.common.auth.guards.MemberGuard;
import com.blog.global.common.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

  private final ImageService imageService;

  @GetMapping("/presigned-url")
  @UseGuards({MemberGuard.class})
  @Operation(summary = "Presigned Url 반환을 위한 API")
  public ResponseDto<String> getPresignedUrl(
      @RequestParam String fileName) {
    String presignedUrl = this.imageService.getPresignedUrl(fileName);
    return ResponseDto.of(200, "Presigned Url 반환 성공", presignedUrl);
  }
}