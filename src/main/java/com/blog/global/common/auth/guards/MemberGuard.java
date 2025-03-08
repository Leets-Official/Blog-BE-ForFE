package com.blog.global.common.auth.guards;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberGuard implements Guard {

  @Override
  public void canActivate() throws Exception {
    // Do Nothing
  }
}
