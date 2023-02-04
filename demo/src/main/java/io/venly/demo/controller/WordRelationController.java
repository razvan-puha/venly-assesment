package io.venly.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterStyle;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.venly.demo.dto.WordRelationDto;
import io.venly.demo.dto.WordRelationRequestBody;
import io.venly.demo.entity.RelationType;
import io.venly.demo.entity.WordRelation;
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

    @Operation(summary = "Add a relation between two words")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201",
            description = "The relation was added to the database"
        ),
        @ApiResponse(responseCode = "400",
            description = "The relation already exists",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)
        )
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addWordRelation(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = WordRelationRequestBody.class))
        )
        @RequestBody
        @Valid
        WordRelationRequestBody requestBody
    ) {
        int newResourceId = wordRelationService.addWordRelation(requestBody);
        throw new ResourceCreatedException(URI.create(String.format("/api/word_relations/%d", newResourceId)));
    }

    @Operation(summary = "Get the list of all relations")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = "The list of stored relations",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                array = @ArraySchema(schema = @Schema(implementation = WordRelationDto.class))
            )
        )
    })
    @GetMapping
    public List<WordRelationDto> getWordRelations(
        @Parameter(description = "Filter by the relation type")
        @RequestParam(value = "relation", required = false)
        String relation,
        @Parameter(description = "Show also the reversed relations")
        @RequestParam(value = "inverse", required = false)
        boolean showAlsoInverse
    ) {
        if (relation == null || relation.isEmpty()) {
            return wordRelationService.getWordRelations();
        } else {
            return wordRelationService.getWordRelationsByRelation(RelationType.from(relation), showAlsoInverse);
        }
    }

    @Operation(
        summary = "Create a search path between the source and the target",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
        )
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = "The result path between the source and the target",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                array = @ArraySchema(schema = @Schema(implementation = String.class))
            )
        ),
        @ApiResponse(responseCode = "400",
            description = "No path has been found between the source and the target",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)
        )
    })
    @GetMapping(value = "/search", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<String> pathSearch(
        @Parameter(description = "The start word of the path")
        @RequestPart(value = "source")
        String source,
        @Parameter(description = "The target word of the path")
        @RequestPart(value = "target")
        String target
    ) {
        return wordRelationService.getPath(source.toLowerCase().trim(), target.toLowerCase().trim());
    }
}