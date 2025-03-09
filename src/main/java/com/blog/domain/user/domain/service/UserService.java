package com.blog.domain.user.domain.service;

import com.blog.domain.user.application.dto.response.UserGetResponse;
import com.blog.domain.user.domain.entity.User;
import com.blog.domain.user.domain.repository.UserRepository;
import com.blog.domain.user.exception.UserNotFoundException;
import com.blog.global.common.auth.MemberContext;
import com.blog.global.common.auth.TokenMemberInfo;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

  public Optional<User> checkLoginAvailableByNickname(String nickname, String password) {
    Optional<User> userOptional = this.userRepository.findByNickname(nickname);
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
}
