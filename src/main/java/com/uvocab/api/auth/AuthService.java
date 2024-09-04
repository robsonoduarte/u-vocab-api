package com.uvocab.api.auth;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import uvocab.protobuf.v1.User;

@Service
@AllArgsConstructor
public class AuthService {

  private final AuthenticationManager authenticationManager;
  private final JwtToken jwtToken;

  public String signIn(User user) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));
    return jwtToken.generateToken(user.getLogin());
  }
}
