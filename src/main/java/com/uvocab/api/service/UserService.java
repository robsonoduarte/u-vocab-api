package com.uvocab.api.service;

import com.uvocab.api.mapper.UserMapper;
import com.uvocab.api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;
import uvocab.protobuf.v1.User;

@Service
@AllArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public User save(User user) {
    return userMapper.toProto(userRepository.save(userMapper.toDomain(user)));
  }

  public Long get(Long id){
    var user = userRepository.findById(id);

    return userMapper.toProto(user);
  }
}
