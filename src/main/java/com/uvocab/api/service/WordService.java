package com.uvocab.api.service;

import com.uvocab.api.mapper.WordMapper;
import com.uvocab.api.repository.WordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uvocab.protobuf.v1.Word;

@Service
@AllArgsConstructor
public class WordService {
  private final WordRepository wordRepository;
  private final WordMapper wordMapper;

  public Word save(Word word) {
    return wordMapper.toProto(wordRepository.save(wordMapper.toDomain(word)));
  }
}
