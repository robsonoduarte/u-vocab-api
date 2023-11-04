package com.uvocab.api.domain

import org.junit.jupiter.api.Test
import static org.junit.jupiter.api.Assertions.assertEquals

class VocabTest {
	@Test
	void test () {
		def vocab = new Vocab("home")
		assertEquals('home', vocab.word());
	}
}
