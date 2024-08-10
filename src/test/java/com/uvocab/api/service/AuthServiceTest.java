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
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthServiceTest {

  @Mock private AuthenticationManager authenticationManager;

  @InjectMocks private AuthService authService;

  @Test()
  void shouldValidateUser() {
    var login =
        uvocab.protobuf.v1.User.newBuilder()
            .setLogin("robson@uvocab.education")
            .setPassword("12345")
            .build();

    Authentication authentication = mock(Authentication.class);
    when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
        .thenReturn(authentication);

    var token = authService.login(login);

    verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
    assertEquals("str_token", token);
    assertEquals(authentication, SecurityContextHolder.getContext().getAuthentication());
  }
}
