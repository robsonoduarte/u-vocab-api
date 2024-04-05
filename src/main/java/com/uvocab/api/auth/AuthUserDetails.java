package com.uvocab.api.auth;

import com.uvocab.api.domain.User;
import com.uvocab.api.repository.UserRepository;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthUserDetails implements UserDetailsService {

  @Autowired private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    User user =
        this.userRepository
            .findByLogin(login)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    return new org.springframework.security.core.userdetails.User(
        user.getLogin(), user.getPassword(), new ArrayList<>());
  }
}
