package com.uvocab.api.service;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uvocab.protobuf.v1.User;

@Service
@AllArgsConstructor
public class AuthService {

  private final AuthenticationManager authenticationManager;

  private static final String FIXED_TOKEN = "str_token";

  public String login(User user) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    if (authentication.isAuthenticated()){
        return FIXED_TOKEN;
    }
    else throw new RuntimeException("Falha ao autenticar");
  }
}
