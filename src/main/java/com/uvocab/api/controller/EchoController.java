package com.uvocab.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/echo")
@AllArgsConstructor
public class EchoController {

  @GetMapping()
  public ResponseEntity<String> get() {
    return ResponseEntity.ok("echo");
  }
}
