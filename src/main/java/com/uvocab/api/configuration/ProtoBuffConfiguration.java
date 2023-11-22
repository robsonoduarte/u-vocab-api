package com.uvocab.api.configuration;

import com.google.protobuf.util.JsonFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.http.converter.protobuf.ProtobufJsonFormatHttpMessageConverter;

@Configuration
class ProtoBuffConfiguration {
  @Bean
  public ProtobufHttpMessageConverter protobufHttpMessageConverter() {
    var parser = JsonFormat.parser();
    var printer = JsonFormat.printer().includingDefaultValueFields();
    return new ProtobufJsonFormatHttpMessageConverter(parser, printer);
  }
}
