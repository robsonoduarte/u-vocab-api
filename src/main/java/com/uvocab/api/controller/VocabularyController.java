package com.uvocab.api.controller;

import com.uvocab.api.service.VocabularyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uvocab.protobuf.v1.Vocabulary;

@RestController
@RequestMapping("/v1/word")
@AllArgsConstructor
public class VocabularyController {
  private final VocabularyService vocabularyService;

  @PostMapping
  public ResponseEntity<Vocabulary> postWord(@RequestBody Vocabulary word) {
    return ResponseEntity.ok(vocabularyService.save(word));
  }
}
