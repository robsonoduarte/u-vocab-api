package com.uvocab.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.uvocab.api.domain.Vocab;
import org.junit.jupiter.api.Test;

public class VocabTest {
  @Test
  void test() {
    Vocab vocab = new Vocab("home");
    assertEquals("home", vocab.word());
  }
}
