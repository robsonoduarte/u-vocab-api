package com.uvocab.api.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import uvocab.protobuf.v1.User;

class UserMapperTest {
  UserMapper mapper = new UserMapper();

  @Test
  void shouldMapperUserProtoToUserDomain() {
    var proto = User.newBuilder().setLogin("danilo").setEmail("danilo@uvocab.edu").build();
    var domain = mapper.toDomain(proto);
    assertEquals("danilo", domain.getLogin());
    assertEquals("danilo@uvocab.edu", domain.getEmail());
  }

  @Test
  void shouldMapperDomainToUserProto() {
    var domain =
        com.uvocab.api.domain.User.builder()
            .id(1)
            .login("robson")
            .email("robson@uvocab.edu")
            .build();
    var proto = mapper.toProto(domain);
    assertEquals(1, proto.getId());
    assertEquals("robson", proto.getLogin());
    assertEquals("robson@uvocab.edu", proto.getEmail());
  }
}
