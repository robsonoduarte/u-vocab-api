package com.uvocab.api.service;

import com.uvocab.api.exception.NotFoundException;
import com.uvocab.api.mapper.UserMapper;
import com.uvocab.api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uvocab.protobuf.v1.User;

@Service
@AllArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;


  private static final String FIXED_TOKEN = "str_token";

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

  public String login(User user){
    var existUser = userRepository.findByLogin(user.getLogin());
    if (existUser.isPresent()){
      var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));
      return FIXED_TOKEN;
    }
    throw new NotFoundException("Login not found");
  }

}
