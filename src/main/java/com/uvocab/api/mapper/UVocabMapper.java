package com.uvocab.api.mapper;

import com.uvocab.api.request.UVocabPostUser;
import org.mapstruct.Mapper;
import uvocab.protobuf.v1.User;

@Mapper(componentModel = "spring")

public abstract class UVocabMapper {
    public abstract User toVocab(UVocabPostUser uVocabPostUser);
}
