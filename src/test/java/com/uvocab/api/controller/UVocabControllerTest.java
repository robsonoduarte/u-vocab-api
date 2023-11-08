package com.uvocab.api.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class UVocabControllerTest {

  private final UVocabController uVocabController = new UVocabController();

  //@Test
  void shouldReturnWordVocabIsNotNull() {
    var word = uVocabController.getVocab();
    assertNotNull(word);
  }
}
