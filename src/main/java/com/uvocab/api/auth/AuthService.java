package com.uvocab.api.auth;

import com.uvocab.api.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import uvocab.protobuf.v1.User;

@Service
@AllArgsConstructor
public class AuthService {

  private static final String FIXED_TOKEN = "str_token";

  private final AuthenticationManager authenticationManager;
  private final UserMapper userMapper;

  public String login(User user) {
    // var userToVerify = userMapper.toDomain(user);
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));
    return FIXED_TOKEN;
  }
}
