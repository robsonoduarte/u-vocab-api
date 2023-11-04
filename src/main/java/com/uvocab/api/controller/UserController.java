package com.uvocab.api.controller;

import com.uvocab.api.repository.UVocabRepository;
import com.uvocab.api.request.UVocabPostUser;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.control.MappingControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uvocab.protobuf.v1.User;

@RestController
@RequestMapping("/v1/user")
@Slf4j
public class UserController {

  @Autowired private UVocabRepository uVocabRepository;

  @PostMapping
  public User postUser(UVocabPostUser uVocabPostUser) {
    User user = User
    return uVocabRepository.save();
  }
}
