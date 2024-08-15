package com.uvocab.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class AuthServiceTest {

  @Mock private AuthenticationManager authenticationManager;

  @InjectMocks private AuthService authService;

  @Test()
  void shouldValidateUser() {
    var login = "robson@uvocab.education";
    var pass = "12345";

    Authentication auth = new UsernamePasswordAuthenticationToken(login, pass);
    when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
        .thenReturn(auth);

    var token = authService.login(login, pass);

    assertEquals("str_token", token);
  }
}
