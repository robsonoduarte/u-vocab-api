package com.uvocab.api.repository;

import com.uvocab.api.domain.Vocabulary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VocabularyRepository extends JpaRepository<Vocabulary, Long> {}
