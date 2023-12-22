package com.uvocab.api.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import uvocab.protobuf.v1.Vocabulary;

class VocabularyMapperTest {
  VocabularyMapper mapper = new VocabularyMapper();

  @Test
  void shouldMapperWordProtoToWordDomain() {
    var proto = Vocabulary.newBuilder().setWord("Home").build();
    var domain = mapper.toDomain(proto);
    assertEquals("Home", domain.getWord());
  }

  @Test
  void shouldMapperDomainToWordProto() {
    var domain = com.uvocab.api.domain.Vocabulary.builder().id(1).word("Home").build();
    var proto = mapper.toProto(domain);
    assertEquals(1, proto.getId());
    assertEquals("Home", proto.getWord());
  }
}
