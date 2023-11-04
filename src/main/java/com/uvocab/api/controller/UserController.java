package com.uvocab.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uvocab.protobuf.v1.User;

@RestController
@RequestMapping("/v1/user")
public class UserController {

  @PostMapping
  public ResponseEntity<String> postUser(@RequestBody User user) {
    return ResponseEntity.ok("");
  }
}
