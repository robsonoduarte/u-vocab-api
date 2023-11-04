package com.uvocab.api.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class UVocabControllerTest {

    private final UVocabController uVocabController = new UVocabController();
    @Test
    void shouldReturnWordVocabIsNotNull(){
        var word = uVocabController.getVocab();
        assertNotNull(word);

    }

}
