package com.uvocab.api.service;

import com.uvocab.api.domain.User;
import com.uvocab.api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
  @Autowired private final UserRepository userRepository;

  public void saveUser(User user) {
    userRepository.save(user);
  }
}
