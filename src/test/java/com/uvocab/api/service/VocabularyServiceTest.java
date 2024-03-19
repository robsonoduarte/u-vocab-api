package com.uvocab.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.uvocab.api.domain.Vocabulary;
import com.uvocab.api.mapper.VocabularyMapper;
import com.uvocab.api.repository.VocabularyRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import uvocab.protobuf.v1.Vocabularies;

class VocabularyServiceTest {

  @Mock private VocabularyRepository vocabularyRepository;

  @Mock private VocabularyMapper vocabularyMapper;

  @InjectMocks private VocabularyService vocabularyService;

  @BeforeEach
  void postTest() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shoudSavetheVocabulary() {
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
    var filter = Filter.builder().pageNumber(2).build();
    var domain = Vocabulary.builder().id(1).build();
    var proto = uvocab.protobuf.v1.Vocabulary.newBuilder().setId(1).build();
    var pageRequest = PageRequest.of(1, 10);
    var page = new PageImpl<>(List.of(domain), pageRequest, 30);
    var vocabulariesExpected =
        Vocabularies.newBuilder()
            .setPageNumber(2)
            .setTotalResults(30)
            .setTotalPages(3)
            .addAllVocabularies(List.of(proto))
            .build();

    when(vocabularyRepository.findAll(pageRequest)).thenReturn(page);
    when(vocabularyMapper.toProto(domain)).thenReturn(proto);

    var vocabularies = vocabularyService.getVocabularies(filter);

    assertEquals(vocabulariesExpected, vocabularies);

    verify(vocabularyMapper).toProto(domain);
    verify(vocabularyRepository).findAll(pageRequest);
  }
}
