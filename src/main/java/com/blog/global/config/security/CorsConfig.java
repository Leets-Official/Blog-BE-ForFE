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

		appConfigProperties.setCorsDomain(List.of(
				"https://kdh-main.d2snz5jzmbs2zl.amplifyapp.com",
				"https://week7-shin-donghyeon.d1z7wqxq02basu.amplifyapp.com/",
				"https://week7-jcw.d3p6q7thzc3f0z.amplifyapp.com",
				"https://seonayoung-7week.d22n5fory5ccqn.amplifyapp.com",
				"http://localhost:3000",
				"https://blog.leets.land",
				"https://chaemin-main.d1m4qmmv68yk0y.amplifyapp.com",
				"https://week10.d1yenc36e2pehi.amplifyapp.com",
				"https://main.d1rq4e5msoe119.amplifyapp.com/",
				"https://leeyeseo-10week.d6k729hko01va.amplifyapp.com/",
				"https://www.gitlog.store/",
				"https://seol-week10.d4kokne1n89co.amplifyapp.com/"
		));

		List<String> corsConfig = this.appConfigProperties.getCorsDomain();

		config.setAllowedOriginPatterns(corsConfig);

		config.addAllowedHeader("*");

		config.addAllowedMethod("*");

		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
