package com.uvocab.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpMethod.POST;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import uvocab.protobuf.v1.User;

@ActiveProfiles({"test"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

  @Autowired private TestRestTemplate restTemplate;

  @Test
  void shouldPostTheUser() {
    var user =
        User.newBuilder()
            .setLogin("Danilo")
            .setEmail("danilossimoes28@gmail.com")
            .build(); // branch no proto criand o user
    var response = restTemplate.exchange("/v1/user", POST, new HttpEntity<>(user), String.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    // assertEquals("OK", response.getBody());
  }
}
