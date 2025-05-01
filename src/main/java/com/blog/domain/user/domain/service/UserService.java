package com.blog.domain.user.domain.service;

import com.blog.domain.user.application.dto.request.UserPatchRequest;
import com.blog.domain.user.application.dto.response.UserGetResponse;
import com.blog.domain.user.domain.entity.User;
import com.blog.domain.user.domain.repository.UserRepository;
import com.blog.domain.user.exception.EmailDuplicateException;
import com.blog.domain.user.exception.NicknameDuplicateException;
import com.blog.domain.user.exception.UserNotFoundException;
import com.blog.global.common.auth.MemberContext;
import com.blog.global.common.auth.TokenMemberInfo;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public Optional<User> checkLoginAvailable(String email, String password) {
    Optional<User> userOptional = this.userRepository.findByEmail(email);
    if (userOptional.isPresent()) {
      User member = userOptional.get();
      if (this.bCryptPasswordEncoder.matches(password, member.getPassword())) {
        return userOptional;
      }
    }
    return Optional.empty();
  }

  public Optional<User> checkLoginAvailableByKakaoId(Long kakaoId) {
    return this.userRepository.findByKakaoId(kakaoId);
  }

  public Optional<User> checkLoginAvailableByEmail(String email, String password) {
    Optional<User> userOptional = this.userRepository.findByEmail(email);
    log.info("userOptional: {}", userOptional);
    log.info("email: {}", email);
    if (userOptional.isPresent()) {
      User member = userOptional.get();
      if (this.bCryptPasswordEncoder.matches(password, member.getPassword())) {
        return userOptional;
      }
    }

    return Optional.empty();
  }

  public User findById(Long id) {
    return this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);
  }

  public boolean checkEmailDuplicate(String email) {
    return this.userRepository.findByEmail(email).isPresent();
  }

  public boolean checkNicknameDuplicate(String nickname) {
    return this.userRepository.findByNickname(nickname).isPresent();
  }

  public String hashPassword(String password) {
    return this.bCryptPasswordEncoder.encode(password);
  }

  @Transactional
  public User save(User user) {
    return this.userRepository.save(user);
  }

  @Transactional
  public void updatePicture(String picture) {
    TokenMemberInfo member = MemberContext.getMember();
    User user = this.findById(member.id());

    user.setProfilePicture(picture);
  }

  @Transactional
  public void updateNickname(String nickname) {
    TokenMemberInfo member = MemberContext.getMember();
    User user = this.findById(member.id());

    user.setNickname(nickname);
  }

  @Transactional
  public void updatePassword(String password) {
    TokenMemberInfo member = MemberContext.getMember();
    User user = this.findById(member.id());

    user.setPassword(this.hashPassword(password));
  }

  public UserGetResponse getMyInfo() {
    TokenMemberInfo member = MemberContext.getMember();

    return UserGetResponse.from(this.findById(member.id()));
  }

  @Transactional
  public void updateUser(UserPatchRequest userPatchRequest) {
    boolean isEmailDuplicate = this.checkEmailDuplicate(userPatchRequest.email());
    if (isEmailDuplicate) {
      throw new EmailDuplicateException();
    }

    boolean isNicknameDuplicate = this.checkNicknameDuplicate(userPatchRequest.nickname());
    if (isNicknameDuplicate) {
      throw new NicknameDuplicateException();
    }

    TokenMemberInfo member = MemberContext.getMember();
    User user = this.findById(member.id());

    String hashedPassword = this.hashPassword(userPatchRequest.password());

    user.updateUserInfoFrom(userPatchRequest, hashedPassword);

    this.save(user);
  }
}
