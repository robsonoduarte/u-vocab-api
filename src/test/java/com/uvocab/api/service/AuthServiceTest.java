package com.uvocab.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

public class AuthServiceTest {

  @InjectMocks private AuthService authService;

  @Test()
  void shouldValidateUser() {
    var login =
        uvocab.protobuf.v1.User.newBuilder()
            .setLogin("robson@uvocab.education")
            .setPassword("12345")
            .build();
    var tokenToReturn = "str_token";

    when(authService.login(login)).thenReturn(tokenToReturn);

    assertEquals(tokenToReturn, "str_token");
  }
}
