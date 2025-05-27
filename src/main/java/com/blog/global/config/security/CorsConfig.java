package com.blog.global.config.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.blog.global.config.properties.AppConfigProperties;

import lombok.RequiredArgsConstructor;

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

		appConfigProperties.setCorsDomain(List.of("https://kdh-main.d2snz5jzmbs2zl.amplifyapp.com",
			"https://week7-shin-donghyeon.d1z7wqxq02basu.amplifyapp.com/"));
		List<String> corsConfig = this.appConfigProperties.getCorsDomain();

		config.setAllowedOriginPatterns(corsConfig);

		config.addAllowedHeader("*");

		config.addAllowedMethod("*");

		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
