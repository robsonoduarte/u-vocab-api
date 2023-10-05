package com.uvocab.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/uvocab")
public class UvocabController {
  @GetMapping()
  public String testMessage() {
    return "OK";
  }
}
