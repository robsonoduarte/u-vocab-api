package com.uvocab.api.auth;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

  private static final String FIXED_TOKEN = "str_token";

  private final AuthenticationManager authenticationManager;

  public String login(String login, String password) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
    return FIXED_TOKEN;
  }
}
