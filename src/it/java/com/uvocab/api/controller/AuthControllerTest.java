package com.uvocab.api.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import uvocab.protobuf.v1.User;

@ActiveProfiles({"test"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthControllerTest {
  @Autowired private TestRestTemplate restTemplate;

  @Test
  void shouldAuthenticateUser() {
    var user = User.newBuilder().setLogin("danilo@uvocab.education").setPassword("12345").build();
    ResponseEntity<String> response =
        restTemplate.exchange("/v1/auth", HttpMethod.POST, new HttpEntity<>(user), String.class);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
  }
}
