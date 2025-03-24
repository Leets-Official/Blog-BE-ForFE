package com.blog.global.config.security;

import com.blog.global.config.properties.AppConfigProperties;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@RequiredArgsConstructor
public class CorsConfig {

  private final AppConfigProperties appConfigProperties;

  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();

    config.setAllowCredentials(true);
//    config.addAllowedOriginPattern(".leets.land");

    List<String> corsConfig = this.appConfigProperties.getCorsDomain();

    config.setAllowedOriginPatterns(corsConfig);

    config.addAllowedHeader("*");

    config.addAllowedMethod("*");

    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }
}
