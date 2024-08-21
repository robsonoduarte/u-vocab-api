package com.uvocab.api.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import lombok.Builder;
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
            new Object[] {"robson@uvocab.education", "12345", OK},
            new Object[] {"danilo@uvocab.education", "12345", FORBIDDEN});

    for (Object[] testCase : testCases) {
      String login = (String) testCase[0];
      String password = (String) testCase[1];
      HttpStatus expectedStatus = (HttpStatus) testCase[2];

      var user = User.newBuilder().setLogin(login).setPassword(password).build();
      ResponseEntity<String> response =
          restTemplate.exchange("/v1/auth", POST, new HttpEntity<>(user), String.class);

      assertEquals(expectedStatus, response.getStatusCode());

      if (expectedStatus == OK) {
        assertNotNull(response.getBody());
      }
    }
  }

  @Test
  void shouldHandleAuthenticationByBravox() {
    // only to compare with your test...
    // I tried to use the "fluent" resources of java 8 and above...
    List.of(
            TestCase.builder()
                .email("robson@uvocab.education")
                .password("12345")
                .status(OK)
                .build(),
            TestCase.builder()
                .email("danilo@uvocab.education")
                .password("12345")
                .status(FORBIDDEN)
                .build())
        .forEach(
            testCase -> {
              var user =
                  User.newBuilder().setLogin(testCase.email).setPassword(testCase.password).build();
              var response =
                  restTemplate.exchange("/v1/auth", POST, new HttpEntity<>(user), String.class);

              assertEquals(testCase.status, response.getStatusCode());

              if (testCase.status == OK) {
                assertNotNull(response.getBody());
              }
            });
  }

  @Builder
  static class TestCase {
    String email;
    String password;
    HttpStatus status;
  }
}
