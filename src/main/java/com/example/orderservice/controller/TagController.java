package com.example.orderservice.controller;

import com.example.orderservice.dto.TagDto;
import com.example.orderservice.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/tags")
@CrossOrigin(origins = "http://localhost:4200")
public class TagController {

    private final TagService tagService;

    // Build Add Tag REST API
    @PostMapping
    public ResponseEntity<TagDto> createTag(@RequestBody TagDto tagDto) {
        TagDto savedTag = tagService.createTag(tagDto);
        return new ResponseEntity<>(savedTag, HttpStatus.CREATED);
    }

    // Build GET Tag by ID REST API
    @GetMapping("{id}")
    public ResponseEntity<TagDto> getTagById(@PathVariable("id") Long tagId) {
        TagDto tagDto = tagService.getTagById(tagId);
        return ResponseEntity.ok(tagDto);
    }

    // Build GET All Tags REST API
    @GetMapping
    public ResponseEntity<List<TagDto>> getAllTags() {
        List<TagDto> tags = tagService.getAllTags();
        return ResponseEntity.ok(tags);
    }

    // Build Update Tag REST API
    @PutMapping("{id}")
    public ResponseEntity<TagDto> updateTag(@PathVariable("id") Long tagId, @RequestBody TagDto updatedTag) {
        TagDto tagDto = tagService.updateTag(tagId, updatedTag);
        return ResponseEntity.ok(tagDto);
    }

    // Build Delete Tag REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTag(@PathVariable("id") Long tagId) {
        tagService.deleteTag(tagId);
        return ResponseEntity.ok("Tag deleted successfully");
    }
}