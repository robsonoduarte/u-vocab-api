package com.uvocab.api.configuration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

class ProtoBuffConfigurationTest {
  private ProtoBuffConfiguration protoBuffConfiguration = new ProtoBuffConfiguration();

  @Test
  void shouldCreateTheProtobufHttpMessageConverterAsSpringConfigurationAndBean() throws Exception {
    var converter = protoBuffConfiguration.protobufHttpMessageConverter();
    var klass = protoBuffConfiguration.getClass();
    assertNotNull(converter);
    assertNotNull(klass.getAnnotation(Configuration.class));
    assertNotNull(
        klass.getDeclaredMethod("protobufHttpMessageConverter").getAnnotation(Bean.class));
  }
}
