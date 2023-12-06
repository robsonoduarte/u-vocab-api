package com.uvocab.api.mapper;

import org.springframework.stereotype.Component;
import uvocab.protobuf.v1.Word;

@Component
public class WordMapper {
  public com.uvocab.api.domain.Word toDomain(Word word) {
    return com.uvocab.api.domain.Word.builder().word(word.getWord()).build();
  }

  public Word toProto(com.uvocab.api.domain.Word word) {
    return Word.newBuilder().setId(word.getId()).setWord(word.getWord()).build();
  }
}
