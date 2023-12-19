package com.uvocab.api.controller;

import com.uvocab.api.mapper.UserMapper;
import com.uvocab.api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uvocab.protobuf.v1.User;

@RestController
@RequestMapping("/v1/user")
@AllArgsConstructor
public class UserController {

  private final UserService userService;
  private final UserMapper userMapper;

  @PostMapping
  public ResponseEntity<User> post(@RequestBody User user) {
    return ResponseEntity.ok(userService.save(user));
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<com.uvocab.api.domain.User> get(@PathVariable long id) {
    return ResponseEntity.ok(userMapper.toDomain(userService.findById(id)));
  }
}
