package com.uvocab.api.auth;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import com.uvocab.api.domain.User;
import com.uvocab.api.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

class AuthUserDetailsTest {

  @Mock UserRepository userRepository;

  @InjectMocks AuthUserDetails authUserDetails;

  @BeforeEach
  void setup() {
    openMocks(this);
  }

  @Test
  void shouldReturnAUserDetailByUserLogin() {
    var login = "robson@uvocab.education";
    var password = "12345";
    var user = User.builder().login(login).password(password).build();

    when(userRepository.findByLogin(login)).thenReturn(Optional.of(user));
    var userDetails = authUserDetails.loadUserByUsername(login);

    assertEquals(user.getLogin(), userDetails.getUsername());
    assertEquals(user.getPassword(), userDetails.getPassword());
  }

  @Test
  void shouldThrowExceptionWhenNotFoundUserByLogin() {
    var login = "";
    when(userRepository.findByLogin(login)).thenReturn(Optional.empty());
    var exception =
        assertThrows(
            UsernameNotFoundException.class,
            () -> {
              authUserDetails.loadUserByUsername(login);
            });
    assertEquals("User not found", exception.getMessage());
  }
}
