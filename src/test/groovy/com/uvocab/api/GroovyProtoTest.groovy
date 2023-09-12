package com.uvocab.api

import org.junit.jupiter.api.Test
import uvocab.protobuf.v1.Proto

import static org.junit.jupiter.api.Assertions.assertEquals

class GroovyProtoTest {
	@Test
	void test() {
		def proto = Proto.newBuilder()
				.setTest("test")
				.build()
		assertEquals('test', proto.getTest());
	}
}
