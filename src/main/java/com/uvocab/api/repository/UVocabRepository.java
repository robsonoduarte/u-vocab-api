package com.uvocab.api.repository;

import org.springframework.data.repository.CrudRepository;
import uvocab.protobuf.v1.User;

public interface UVocabRepository extends CrudRepository<User, Long> {
}
