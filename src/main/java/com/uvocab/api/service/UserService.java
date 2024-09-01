package com.uvocab.api.service;

import com.uvocab.api.exception.NotFoundException;
import com.uvocab.api.mapper.UserMapper;
import com.uvocab.api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uvocab.protobuf.v1.User;

@Service
@AllArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  public User save(User user) {
    var passwordEncrypt = passwordEncoder.encode(user.getPassword());
    var userToSave = userMapper.toDomain(user);
    userToSave.setPassword(passwordEncrypt);
    return userMapper.toProto(userRepository.save(userToSave));
  }

  public User findById(long id) {
    var optional = userRepository.findById(id);
    if (optional.isPresent()) {
      return userMapper.toProto(optional.get());
    }
    throw new NotFoundException("User not found to id: " + id);
  }
}
