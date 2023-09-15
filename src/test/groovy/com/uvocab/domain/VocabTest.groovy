package com.uvocab.domain

import org.junit.jupiter.api.Test
import static org.junit.jupiter.api.Assertions.assertEquals

class VocabTest {
	@Test
	void test () {
		def words = new Vocab(1)

		assertEquals(1,words)
	}
}
