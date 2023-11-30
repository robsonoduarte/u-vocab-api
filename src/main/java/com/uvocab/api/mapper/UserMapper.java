package com.uvocab.api.mapper;

import org.springframework.stereotype.Component;
import uvocab.protobuf.v1.User;

@Component
public class UserMapper {
  public com.uvocab.api.domain.User toDomain(User user) {
    return com.uvocab.api.domain.User.builder()
        .login(user.getLogin())
        .email(user.getEmail())
        .build();
  }

  public User toProto(com.uvocab.api.domain.User user) {
    return User.newBuilder()
        .setId(user.getId())
        .setLogin(user.getLogin())
        .setEmail(user.getEmail())
        .build();
  }
}
