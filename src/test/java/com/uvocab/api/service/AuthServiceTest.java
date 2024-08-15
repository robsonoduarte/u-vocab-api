package com.uvocab.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.uvocab.api.TestBase;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AuthServiceTest extends TestBase {

  @Mock private AuthenticationManager authenticationManager;

  @InjectMocks private AuthService authService;

  @Test()
  void shouldValidateUser() {
    var login = "robson@uvocab.education";
    var pass = "12345";
    var token = authService.login(login, pass);
    assertEquals("str_token", token);
    verify(authenticationManager)
        .authenticate(new UsernamePasswordAuthenticationToken(login, pass));
  }
}
