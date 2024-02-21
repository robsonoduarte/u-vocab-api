package com.uvocab.api.service;

import com.uvocab.api.mapper.VocabularyMapper;
import com.uvocab.api.repository.VocabularyRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uvocab.protobuf.v1.Vocabularies;
import uvocab.protobuf.v1.Vocabulary;

@Service
@AllArgsConstructor
public class VocabularyService {
  private final VocabularyRepository vocabularyRepository;
  private final VocabularyMapper vocabularyMapper;

  public Vocabulary save(Vocabulary word) {
    return vocabularyMapper.toProto(vocabularyRepository.save(vocabularyMapper.toDomain(word)));
  }

  public Vocabularies getVocabularies(Filter filter) {
    var page = vocabularyRepository.findAll(PageRequest.of(filter.getPageNumber() - 1, 10));
    return Vocabularies.newBuilder()
        .addAllVocabularies(page.getContent().stream().map(vocabularyMapper::toProto).toList())
        .setPageNumber(page.getPageable().getPageNumber() + 1)
        .setTotalPages(page.getTotalPages())
        .setTotalResults(page.getTotalElements())
        .build();
  }
}
