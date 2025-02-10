package com.example.orderservice.mapper;

import com.example.orderservice.dto.TagDto;
import com.example.orderservice.entity.Tag;

public class TagMapper {

    public static TagDto mapToTagDto(Tag tag) {
        return new TagDto(
                tag.getId(),
                tag.getName()
        );
    }

    public static Tag mapToTag(TagDto tagDto) {
        return new Tag(
                tagDto.getId(),
                tagDto.getName()
        );
    }
}