package com.uvocab.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.uvocab.api.TestBase;
import com.uvocab.api.exception.NotFoundException;
import com.uvocab.api.mapper.UserMapper;
import com.uvocab.api.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;

class UserServiceTest extends TestBase {

  @Mock private UserRepository userRepository;

  @Mock private UserMapper userMapper;

  @Mock private PasswordEncoder passwordEncoder;

  @InjectMocks private UserService userService;

  @Test
  void shouldSaveTheUser() {
    var password = "123";
    var passwordCrypt = "xpto";
    var domain = com.uvocab.api.domain.User.builder().id(1).build();
    var domainToSave = com.uvocab.api.domain.User.builder().id(1).password(passwordCrypt).build();
    var proto = uvocab.protobuf.v1.User.newBuilder().setId(1).setPassword(password).build();

    when(userMapper.toDomain(proto)).thenReturn(domain);
    when(userMapper.toProto(domain)).thenReturn(proto);
    when(userRepository.save(domainToSave)).thenReturn(domain);
    when(passwordEncoder.encode(password)).thenReturn(passwordCrypt);

    var protoSaved = userService.save(proto);

    assertEquals(protoSaved, proto);

    verify(userMapper).toDomain(proto);
    verify(userMapper).toProto(domain);
    verify(userRepository).save(domainToSave);
    verify(passwordEncoder).encode(password);
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
  void shouldThrowAException() {
    var id = 0L;
    when(userRepository.findById(id)).thenReturn(Optional.empty());
    Exception thrown = assertThrows(NotFoundException.class, () -> userService.findById(id));
    assertEquals("User not found to id: " + id, thrown.getMessage());
  }

  @Test()
  void shouldValidateUser() {
    var login =
        uvocab.protobuf.v1.User.newBuilder()
            .setLogin("robson@uvocab.education")
            .setPassword("12345")
            .build();
    var tokenToReturn = "str_token";

    when(userService.login(login)).thenReturn(tokenToReturn);

    assertEquals(tokenToReturn, "str_token");

  }
}
