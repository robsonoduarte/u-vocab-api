package com.uvocab.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.HttpMethod.POST;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import uvocab.protobuf.v1.User;

@ActiveProfiles({"test"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
class UserControllerTest {
  @Autowired private TestRestTemplate restTemplate;
  private int id = 0;

  @Test
  @Order(1)
  void shouldPostTheUser() {
    var user = User.newBuilder().setLogin("danilo").setEmail("danilo@uvocab.edu").build();
    var response = restTemplate.exchange("/v1/user", POST, new HttpEntity<>(user), User.class);
    id = (int) response.getBody().getId();
    assertTrue(response.getBody().getId() > 0);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("danilo", user.getLogin());
    assertEquals("danilo@uvocab.edu", user.getEmail());
  }

  @Test
  @Order(2)
  void shouldGetTheUser() {
    var response = restTemplate.getForEntity("/v1/user/" + id, User.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }
}
