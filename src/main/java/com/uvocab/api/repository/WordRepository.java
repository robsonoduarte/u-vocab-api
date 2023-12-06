package com.uvocab.api.repository;

import com.uvocab.api.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long> {}
