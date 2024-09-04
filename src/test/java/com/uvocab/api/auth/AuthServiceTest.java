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
  @Mock private JwtToken jwtToken;

  @InjectMocks private AuthService authService;

  @Test()
  void shouldSignInTheUser() {
    var user = User.newBuilder().setLogin("danilo@uvocab.education").setPassword("12345").build();
    var tokenToReturn = "str_token";

    when(jwtToken.generateToken(user.getLogin())).thenReturn(tokenToReturn);

    var token = authService.signIn(user);

    assertEquals(tokenToReturn, token);

    verify(authenticationManager)
        .authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));
  }
}
