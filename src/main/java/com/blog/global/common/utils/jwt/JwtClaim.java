package com.blog.global.common.utils.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum JwtClaim {

  ID("id"),
  EMAIL("email"),
  NICKNAME("nickname");

  private final String claim;
}
