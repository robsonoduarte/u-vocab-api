package com.uvocab.api.mapper;

import org.springframework.stereotype.Component;
import uvocab.protobuf.v1.Vocabulary;

@Component
public class VocabularyMapper {
  public com.uvocab.api.domain.Vocabulary toDomain(Vocabulary vocabulary) {
    return com.uvocab.api.domain.Vocabulary.builder().word(vocabulary.getWord()).build();
  }

  public Vocabulary toProto(com.uvocab.api.domain.Vocabulary vocabulary) {
    return Vocabulary.newBuilder().setId(vocabulary.getId()).setWord(vocabulary.getWord()).build();
  }
}
