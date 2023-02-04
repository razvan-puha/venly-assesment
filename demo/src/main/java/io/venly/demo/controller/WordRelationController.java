package io.venly.demo.controller;

import io.venly.demo.dto.WordRelationDto;
import io.venly.demo.dto.WordRelationRequestBody;
import io.venly.demo.entity.RelationType;
import io.venly.demo.exception.ResourceCreatedException;
import io.venly.demo.service.WordRelationService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/word_relations")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WordRelationController {
    WordRelationService wordRelationService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addWordRelation(@RequestBody @Valid WordRelationRequestBody requestBody) {
        int newResourceId = wordRelationService.addWordRelation(requestBody);
        throw new ResourceCreatedException(URI.create(String.format("/api/word_relations/%d", newResourceId)));
    }

    @GetMapping
    public List<WordRelationDto> getWordRelations(
        @RequestParam(value = "relation", required = false) String relation,
        @RequestParam(value = "inverse", required = false) boolean showAlsoInverse
    ) {
        if (relation == null || relation.isEmpty()) {
            return wordRelationService.getWordRelations();
        } else {
            return wordRelationService.getWordRelationsByRelation(RelationType.from(relation), showAlsoInverse);
        }
    }

    @GetMapping(value = "/search",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<String> pathSearch(
        @RequestPart(value = "source", required = false) String source,
        @RequestPart(value = "target", required = false) String target
    ) {
        return wordRelationService.getPath(source.toLowerCase().trim(), target.toLowerCase().trim());
    }
}