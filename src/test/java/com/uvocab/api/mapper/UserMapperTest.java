package com.uvocab.api.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import uvocab.protobuf.v1.User;

public class UserMapperTest {
  UserMapper mapper = new UserMapper();

  @Test
  void shouldMapperUserProtoToUserRecord() {
    var proto = User.newBuilder().setLogin("danilo").setEmail("danilo@uvocab.edu").build();
    var record = mapper.toDomain(proto);
    assertEquals("danilo", record.getLogin());
    assertEquals("danilo@uvocab.edu", record.getEmail());
  }

  @Test
  void shouldMapperRecordToUserProto() {
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
