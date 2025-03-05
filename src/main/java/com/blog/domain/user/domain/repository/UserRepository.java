package com.blog.domain.user.domain.repository;

import com.blog.domain.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
