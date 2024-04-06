package com.uvocab.api.auth;

import static java.util.List.of;

import com.uvocab.api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthUserDetails implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    return this.userRepository
        .findByLogin(login)
        .map(user -> new User(user.getLogin(), user.getPassword(), of()))
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }
}
