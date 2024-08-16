package com.uvocab.api.controller;

import com.uvocab.api.auth.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uvocab.protobuf.v1.User;

@RestController
@RequestMapping("/v1/auth")
@AllArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping
  public ResponseEntity<String> validate(@RequestBody User user) {
    return ResponseEntity.ok(authService.login(user));
  }
}
