package com.uvocab.api.service;

import com.uvocab.api.exception.NotFoundException;
import com.uvocab.api.mapper.VocabularyMapper;
import com.uvocab.api.repository.VocabularyRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uvocab.protobuf.v1.SearchRequest;
import uvocab.protobuf.v1.Vocabulary;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class VocabularyService {
  private final VocabularyRepository vocabularyRepository;
  private final VocabularyMapper vocabularyMapper;

  public Vocabulary save(Vocabulary word) {
    return vocabularyMapper.toProto(vocabularyRepository.save(vocabularyMapper.toDomain(word)));
  }

  public List<com.uvocab.api.domain.Vocabulary> getAllWords(int pageNum, int pageSize){
    Pageable pagination = PageRequest.of(pageNum, pageSize);

    Page<com.uvocab.api.domain.Vocabulary> resultPage = vocabularyRepository.findAll(pagination);

    if(resultPage.hasContent()){
      return resultPage.getContent();
    } else {
      throw new NotFoundException("Not Found");
    }
  }
}
