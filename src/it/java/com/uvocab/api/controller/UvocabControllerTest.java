package com.uvocab.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpMethod.GET;

import com.uvocab.api.domain.Vocab;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"test"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UvocabControllerTest {

  @Autowired private TestRestTemplate restTemplate;

  @Test
  void shouldReturnVocabWitWorkOK() {
    var response = restTemplate.exchange("/v1/uvocab", GET, null, Vocab.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("WORD", response.getBody().getWord());
  }
}
