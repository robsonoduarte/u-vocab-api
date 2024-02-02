package com.uvocab.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.uvocab.api.domain.Vocabulary;
import com.uvocab.api.mapper.VocabularyMapper;
import com.uvocab.api.repository.VocabularyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uvocab.protobuf.v1.Vocabularies;

class VocabularyServiceTest {

  @Mock private VocabularyRepository vocabularyRepository;

  @Mock private VocabularyMapper vocabularyMapper;

  @InjectMocks private VocabularyService vocabularyService;

  @BeforeEach
  void postWord() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shoudSavetheWord() {
    var domain = Vocabulary.builder().id(1).build();
    var proto = uvocab.protobuf.v1.Vocabulary.newBuilder().setId(1).build();

    when(vocabularyMapper.toDomain(proto)).thenReturn(domain);
    when(vocabularyMapper.toProto(domain)).thenReturn(proto);
    when(vocabularyRepository.save(domain)).thenReturn(domain);

    var protoSaved = vocabularyService.save(proto);

    assertEquals(protoSaved, proto);

    verify(vocabularyMapper).toDomain(proto);
    verify(vocabularyMapper).toProto(domain);
    verify(vocabularyRepository).save(domain);
  }

  @Test
  void shouldReturnPagination() {
    var results =
        Vocabularies.newBuilder().setPageNumber(1).setTotalResults(10).setPageNumber(1).build();

    verify(vocabularyRepository).getVocabularyPagination(results);
  }
}
