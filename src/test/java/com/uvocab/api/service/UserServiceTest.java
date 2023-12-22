package com.uvocab.api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.uvocab.api.exception.NotFoundException;
import com.uvocab.api.mapper.UserMapper;
import com.uvocab.api.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserServiceTest {

  @Mock private UserRepository userRepository;

  @Mock private UserMapper userMapper;

  @InjectMocks private UserService userService;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldSaveTheUser() {
    var domain = com.uvocab.api.domain.User.builder().id(1).build();
    var proto = uvocab.protobuf.v1.User.newBuilder().setId(1).build();

    when(userMapper.toDomain(proto)).thenReturn(domain);
    when(userMapper.toProto(domain)).thenReturn(proto);
    when(userRepository.save(domain)).thenReturn(domain);

    var protoSaved = userService.save(proto);

    assertEquals(protoSaved, proto);

    verify(userMapper).toDomain(proto);
    verify(userMapper).toProto(domain);
    verify(userRepository).save(domain);
  }

  @Test
  void shouldFindTheUserById() {
    var id = 1L;
    var domain = com.uvocab.api.domain.User.builder().id(1).build();
    var proto = uvocab.protobuf.v1.User.newBuilder().setId(1).build();

    when(userRepository.findById(id)).thenReturn(Optional.of(domain));
    when(userMapper.toProto(domain)).thenReturn(proto);

    var foundUser = userService.findById(id);

    assertEquals(proto, foundUser);

    verify(userMapper).toProto(domain);
    verify(userRepository).findById(id);
  }

  @Test()
  void shouldThrowAException() throws Exception {
    var id = 0L;
    when(userRepository.findById(id)).thenReturn(Optional.empty());
    Exception thrown =
        assertThrows(
            NotFoundException.class,
            () -> {
              userService.findById(id);
            });
    assertEquals("User not found to id: " + id, thrown.getMessage());
  }
}
