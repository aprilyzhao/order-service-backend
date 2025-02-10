package com.example.orderservice.service.impl;

import com.example.orderservice.dto.TagDto;
import com.example.orderservice.entity.Tag;
import com.example.orderservice.exception.ResourceNotFoundException;
import com.example.orderservice.mapper.TagMapper;
import com.example.orderservice.repository.TagRepository;
import com.example.orderservice.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public TagDto createTag(TagDto tagDto) {
        Tag tag = TagMapper.mapToTag(tagDto);
        Tag savedTag = tagRepository.save(tag);
        return TagMapper.mapToTagDto(savedTag);
    }

    @Override
    public TagDto getTagById(Long tagId) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException("Tag does not exist with given id: " + tagId));
        return TagMapper.mapToTagDto(tag);
    }

    @Override
    public List<TagDto> getAllTags() {
        List<Tag> tags = tagRepository.findAll();
        return tags.stream()
                .map(TagMapper::mapToTagDto)
                .collect(Collectors.toList());
    }

    @Override
    public TagDto updateTag(Long tagId, TagDto updatedTag) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException("Tag does not exist with given id: " + tagId));

        tag.setName(updatedTag.getName());

        Tag updatedTagObj = tagRepository.save(tag);
        return TagMapper.mapToTagDto(updatedTagObj);
    }

    @Override
    public void deleteTag(Long tagId) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException("Tag does not exist with given id: " + tagId));
        tagRepository.deleteById(tagId);
    }
}