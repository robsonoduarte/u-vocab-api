package com.uvocab.api.service;

import com.uvocab.api.mapper.VocabularyMapper;
import com.uvocab.api.repository.VocabularyRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
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

  public Vocabularies getVocabularyPagination(int total_results, int page_number, int total_pages) {
    Page<Vocabulary> vocabularies =
        vocabularyMapper.toProto(
            vocabularyRepository.findAll(
                vocabularyMapper.toDomain(PageRequest.of(total_results, page_number))));

    return Vocabularies.newBuilder()
        .addAllVocabularies(vocabularies.getContent())
        .setPageNumber(vocabularies.getNumber())
        .setTotalPages(vocabularies.getTotalPages())
        .build();
  }
}
