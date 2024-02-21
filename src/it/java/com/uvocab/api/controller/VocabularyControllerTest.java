package com.uvocab.api.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import uvocab.protobuf.v1.Vocabularies;
import uvocab.protobuf.v1.Vocabulary;

@ActiveProfiles({"test"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VocabularyControllerTest {
  @Autowired private TestRestTemplate restTemplate;

  @Test
  void shouldpostWord() {
    var vocabulary = Vocabulary.newBuilder().setWord("Home").build();
    var response =
        restTemplate.exchange(
            "/v1/word", HttpMethod.POST, new HttpEntity<>(vocabulary), Vocabulary.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertTrue(response.getBody().getId() > 0);
    assertEquals("Home", vocabulary.getWord());
  }

  @Test
  void shouldList() {
    var response = restTemplate.getForEntity("/v1/word/list?pageNumber=1", Vocabularies.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(10, response.getBody().getVocabulariesCount());
    assertEquals(1, response.getBody().getPageNumber());
  }
}
