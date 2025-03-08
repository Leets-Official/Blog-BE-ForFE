package com.blog.global.common.auth;


public class MemberContext {

  private static final ThreadLocal<TokenMemberInfo> currentMember = new ThreadLocal<>();

  public static void setMember(TokenMemberInfo member) {
    currentMember.set(member);
  }

  public static TokenMemberInfo getMember() {
    return currentMember.get();
  }

  public static void clear() {
    currentMember.remove();
  }
}
