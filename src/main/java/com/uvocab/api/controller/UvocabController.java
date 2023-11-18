package com.uvocab.api.controller;

import com.uvocab.api.domain.Vocab;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/uvocab")
public class UvocabController {
  @GetMapping
  public ResponseEntity<Vocab> getVocab() {
    var vocab = new Vocab();
    vocab.setWord("WORD");
    return ResponseEntity.ok(vocab);
  }
}
