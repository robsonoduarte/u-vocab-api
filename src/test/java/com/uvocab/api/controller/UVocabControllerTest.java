package com.uvocab.api.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UVocabControllerTest {

  private final UVocabController uVocabController = new UVocabController();

  // @Test
  void shouldReturnWordVocabIsNotNull() {
    var word = uVocabController.getVocab();
    assertNotNull(word);
  }
}
