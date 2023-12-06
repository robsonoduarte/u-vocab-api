package com.uvocab.api.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import uvocab.protobuf.v1.Word;

@ActiveProfiles({"test"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WordControllerTest {
  @Autowired private TestRestTemplate restTemplate;

  @Test
  void shouldpostWord() {
    var word = Word.newBuilder().setId(1).setWord("Home").build();
    var response =
        restTemplate.exchange("/v1/word", HttpMethod.POST, new HttpEntity<>(word), Word.class);
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals(1, response.getBody().getId());
    Assertions.assertEquals("Home", word.getWord());
  }
}
