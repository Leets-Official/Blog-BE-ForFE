package com.blog.global.common.oauth;


import com.blog.domain.auth.entity.enums.OAuthProvider;
import com.blog.domain.user.domain.entity.User;

public record MemberInfoFromProviders(
    Long id,
    String email,
    String nickname,
    String picture,
    String accessToken,
    OAuthProvider provider
) {

  public MemberInfoFromProviders {
    if (nickname != null) {
      nickname = nickname.replace(" ", "");
    }
  }

  public User toUser(String dummyPassword) {
    return User.builder()
        .email(email)
        .password(dummyPassword)
        .profilePicture(picture)
        .nickname(nickname)
        .build();
  }
}
