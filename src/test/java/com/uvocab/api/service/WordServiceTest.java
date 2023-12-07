package com.uvocab.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.uvocab.api.mapper.WordMapper;
import com.uvocab.api.repository.WordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class WordServiceTest {

  @Mock private WordRepository wordRepository;

  @Mock private WordMapper wordMapper;

  @InjectMocks private WordService wordService;

  @BeforeEach
  void postWord() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shoudSavetheWord() {
    var domain = com.uvocab.api.domain.Word.builder().id(1).build();
    var proto = uvocab.protobuf.v1.Word.newBuilder().setId(1).build();

    when(wordMapper.toDomain(proto)).thenReturn(domain);
    when(wordMapper.toProto(domain)).thenReturn(proto);
    when(wordRepository.save(domain)).thenReturn(domain);

    var protoSaved = wordService.save(proto);

    assertEquals(protoSaved, proto);

    verify(wordMapper).toDomain(proto);
    verify(wordMapper).toProto(domain);
    verify(wordRepository).save(domain);
  }
}
