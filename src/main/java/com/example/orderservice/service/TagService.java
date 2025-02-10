package com.example.orderservice.service;

import com.example.orderservice.dto.TagDto;

import java.util.List;

public interface TagService {

        TagDto createTag(TagDto tagDto);

        TagDto getTagById(Long tagId);

        List<TagDto> getAllTags();

        TagDto updateTag(Long tagId, TagDto updatedTag);

        void deleteTag(Long tagId);
    }

