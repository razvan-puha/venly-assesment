package io.venly.demo.dto;

import io.venly.demo.entity.RelationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
public class WordRelationRequestBody {

    @NotBlank(message = "First word is required")
    @Pattern(regexp = "[\\sA-Za-z]+", message = "Word one: Only characters from A-Z, a-z and spaces are allowed")
    String wordOne;

    @NotBlank(message = "Second word is required")
    @Pattern(regexp = "[\\sA-Za-z]+", message = "Word two: Only characters from A-Z, a-z and spaces are allowed")
    String wordTwo;

    @NotBlank(message = "A relation is required")
    @Pattern(regexp = "[\\sA-Za-z]+", message = "Relation: Only characters from A-Z, a-z and spaces are allowed")
    String relation;
}