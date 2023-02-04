package io.venly.demo.controller;

import io.venly.demo.dto.WordRelationDto;
import io.venly.demo.dto.WordRelationRequestBody;
import io.venly.demo.service.WordRelationService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/word_relations")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WordRelationController {
    WordRelationService wordRelationService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity.BodyBuilder addWordRelation(@RequestBody WordRelationRequestBody requestBody) {
        int createdEntityId = wordRelationService.addWordRelation(requestBody);
        return ResponseEntity.status(HttpStatus.CREATED);
    }

    @GetMapping
    public List<WordRelationDto> getWordRelations() {
        return wordRelationService.getWordRelations();
    }
}