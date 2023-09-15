package com.uvocab.domain;

import lombok.Data;

@Data
public class Vocab {
  private int wordsSearch;

  public Vocab() {}

  public Vocab(int wordsSearch) {
    this.wordsSearch = wordsSearch;
  }
}
