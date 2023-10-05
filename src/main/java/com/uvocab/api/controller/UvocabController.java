package com.uvocab.api.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/uvocab")
public class UvocabController {
  @GetMapping
  public String testMessage() {
    return "OK";
  }
}
