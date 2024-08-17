package com.uvocab.api.auth;

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

  public String login(User user) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));
    return FIXED_TOKEN;
  }
}
