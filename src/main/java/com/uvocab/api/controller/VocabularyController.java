package com.uvocab.api.controller;

import com.uvocab.api.service.Filter;
import com.uvocab.api.service.VocabularyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uvocab.protobuf.v1.Vocabularies;
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

  @GetMapping("/list")
  public ResponseEntity<Vocabularies> listVocab(Filter filter) {
    return ResponseEntity.ok(vocabularyService.getVocabularies(filter));
  }
}
