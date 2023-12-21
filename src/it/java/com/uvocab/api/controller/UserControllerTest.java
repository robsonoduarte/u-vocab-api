package com.uvocab.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.HttpMethod.POST;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import uvocab.protobuf.v1.User;

@ActiveProfiles({"test"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {
  @Autowired private TestRestTemplate restTemplate;

  @Test
  void shouldGetTheUser() {
    var id = 1;
    var response = restTemplate.getForEntity("/v1/user/" + id, User.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(id, response.getBody().getId());
    assertEquals("robson@uvocab.education", response.getBody().getLogin());
    assertEquals("robson@uvocab.education", response.getBody().getEmail());
  }

  @Test
  void shouldReturnExceptionNotFound() {
    var id = 0;
    ResponseEntity<String> response = restTemplate.getForEntity("/v1/user/" + id, String.class);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("User not found to id: " + id, response.getBody());
  }

  @Test
  void shouldPostTheUser() {
    var user =
        User.newBuilder()
            .setLogin("danilo@uvocab.education")
            .setEmail("danilo@uvocab.education")
            .build();
    var response = restTemplate.exchange("/v1/user", POST, new HttpEntity<>(user), User.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(user.getLogin(), response.getBody().getLogin());
    assertEquals(user.getEmail(), response.getBody().getEmail());
    assertTrue(response.getBody().getId() > 1);
  }
}
