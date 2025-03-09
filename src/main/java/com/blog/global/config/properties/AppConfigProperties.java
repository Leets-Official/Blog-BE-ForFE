package com.blog.global.config.properties;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
@Getter
@Setter
public class AppConfigProperties {
  
  private String baseUrl;
  private String defaultProfilePicture;
  private String oauthDummyPassword;
  private String baseServerUrl;
  private List<String> corsDomain;
}