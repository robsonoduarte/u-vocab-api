package com.uvocab.api.configuration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ProtoBuffConfigurationTest {
  private ProtoBuffConfiguration configuration = new ProtoBuffConfiguration();

  @Test
  void shouldCreateTheProtobufHttpMessageConverterAsBean() {
    var protobufHttpMessageConverter = configuration.protobufHttpMessageConverter();
    assertNotNull(protobufHttpMessageConverter);
  }
}
