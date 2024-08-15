package com.uvocab.api.service;

import com.uvocab.api.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

  private final AuthenticationManager authenticationManager;
  private final UserMapper userMapper;

  private static final String FIXED_TOKEN = "str_token";

  public String login(String login, String password) throws AuthenticationException {

    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
    return FIXED_TOKEN;
  }
}
