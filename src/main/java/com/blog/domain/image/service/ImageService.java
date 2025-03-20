package com.blog.domain.image.service;

import com.blog.global.common.utils.S3Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {

  private final S3Utils s3Utils;

  public String getPresignedUrl(String fileName) {
    return this.s3Utils.generatePresignedUrl(fileName);
  }
}
