package com.uvocab.api.service;

import com.uvocab.api.mapper.VocabularyMapper;
import com.uvocab.api.repository.VocabularyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uvocab.protobuf.v1.Vocabulary;

@Service
@AllArgsConstructor
public class VocabularyService {
  private final VocabularyRepository vocabularyRepository;
  private final VocabularyMapper vocabularyMapper;

  public Vocabulary save(Vocabulary word) {
    return vocabularyMapper.toProto(vocabularyRepository.save(vocabularyMapper.toDomain(word)));
  }
}
