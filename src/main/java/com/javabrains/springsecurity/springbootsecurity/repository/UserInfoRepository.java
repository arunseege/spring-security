package com.javabrains.springsecurity.springbootsecurity.repository;

import com.javabrains.springsecurity.springbootsecurity.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByName(String username);


}