package com.uvocab.api.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpMethod.POST;

import java.util.List;
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
public class AuthControllerTest {
  @Autowired private TestRestTemplate restTemplate;

  @Test
  void shouldHandleAuthentication() {

    List<Object[]> testCases =
        List.of(
            new Object[] {"robson@uvocab.education", "12345", HttpStatus.OK},
            new Object[] {"danilo@uvocab.education", "12345", HttpStatus.FORBIDDEN});

    for (Object[] testCase : testCases) {
      String login = (String) testCase[0];
      String password = (String) testCase[1];
      HttpStatus expectedStatus = (HttpStatus) testCase[2];

      var user = User.newBuilder().setLogin(login).setPassword(password).build();
      ResponseEntity<String> response =
          restTemplate.exchange("/v1/auth", POST, new HttpEntity<>(user), String.class);

      assertEquals(expectedStatus, response.getStatusCode());

      if (expectedStatus == HttpStatus.OK) {
        assertNotNull(response.getBody());
      }
    }
  }
}
