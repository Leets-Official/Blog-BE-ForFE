package com.blog.global.common.utils;

import java.time.Duration;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

@Component
@RequiredArgsConstructor
public class S3Utils {

  private final S3Presigner s3Presigner;

  @Value("${s3.buket-name}")
  private String bucket;

  public String generatePresignedUrl(String fileName) {
    String key = generateKey(fileName);

    PutObjectRequest putObjectRequest = PutObjectRequest.builder()
        .bucket(bucket)
        .key(key)
        .build();

    PutObjectPresignRequest request = PutObjectPresignRequest.builder()
        .signatureDuration(Duration.ofMinutes(5))
        .putObjectRequest(putObjectRequest)
        .build();

    PresignedPutObjectRequest presignedUrlRequest = this.s3Presigner.presignPutObject(request);

    return presignedUrlRequest.url().toString();
  }

  private String generateKey(String fileName) {
    String key = UUID.randomUUID().toString();
    String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

    return key + "." + extension;
  }
}
