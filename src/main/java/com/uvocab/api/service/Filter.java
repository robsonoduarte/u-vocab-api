package com.uvocab.api.service;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Filter {
  private int pageNumber;
}
