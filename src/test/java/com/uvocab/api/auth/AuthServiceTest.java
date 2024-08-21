package com.uvocab.api.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.uvocab.api.TestBase;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import uvocab.protobuf.v1.User;

public class AuthServiceTest extends TestBase {

  @Mock private AuthenticationManager authenticationManager;

  @InjectMocks private AuthService authService;

  @Test()
  void shouldSignInTheUser() {
    var user = User.newBuilder().setLogin("danilo@uvocab.education").setPassword("12345").build();
    var token = authService.signIn(user);
    assertEquals("str_token", token);
    verify(authenticationManager)
        .authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));
  }
}
