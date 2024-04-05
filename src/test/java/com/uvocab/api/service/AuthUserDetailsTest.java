package com.uvocab.api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.uvocab.api.auth.AuthUserDetails;
import com.uvocab.api.domain.User;
import com.uvocab.api.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthUserDetailsTest {

  @Mock private UserRepository userRepository;

  @InjectMocks private AuthUserDetails authUserDetails;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldReturnUserByLogin() {
    var login = "robson@uvocab.education";
    var password = "12345";
    var user = User.builder().login(login).password(password).build();

    when(userRepository.findByLogin(login)).thenReturn(Optional.of(user));
    UserDetails userDetails = authUserDetails.loadUserByUsername(login);

    assertEquals(user.getLogin(), userDetails.getUsername());
    assertEquals(user.getPassword(), userDetails.getPassword());
  }

  @Test
  void shouldReturnException() {
    var login = "";
    when(userRepository.findByLogin(login)).thenReturn(Optional.empty());
    Exception exception =
        assertThrows(
            UsernameNotFoundException.class,
            () -> {
              authUserDetails.loadUserByUsername(login);
            });

    assertEquals("User not found", exception.getMessage());
  }
}
