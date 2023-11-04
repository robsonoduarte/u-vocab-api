package com.uvocab.api;

import com.uvocab.api.domain.Vocab;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class VocabTest {
    @Test
    void test(){
        Vocab vocab = new Vocab("home");
        assertEquals("home", vocab.word());
    }
}
