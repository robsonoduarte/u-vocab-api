package com.uvocab.api.controller;

import com.uvocab.api.service.WordService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uvocab.protobuf.v1.Word;

@RestController
@RequestMapping("/v1/word")
@AllArgsConstructor
public class WordController {
  private final WordService wordService;

  @PostMapping
  public ResponseEntity<Word> postWord(@RequestBody Word word) {
    return ResponseEntity.ok(wordService.save(word));
  }
}
