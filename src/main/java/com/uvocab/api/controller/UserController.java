package com.uvocab.api.controller;

import com.uvocab.api.mapper.UserMapper;
import com.uvocab.api.repository.UserRepository;
import com.uvocab.api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uvocab.protobuf.v1.User;

@RestController
@RequestMapping("/v1/user")
@AllArgsConstructor
public class UserController {

  private final UserMapper userMapper;
  private final UserService userService;

  @PostMapping
  public ResponseEntity<User> postUser(@RequestBody User user) {
    userService.saveUser(userMapper.toDomain(user));


    return ResponseEntity.ok(user);

  }
}
