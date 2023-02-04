package io.venly.demo.dto;

import io.venly.demo.entity.RelationType;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WordRelationRequestBody {

    @NotBlank(message = "First word is required")
    String wordOne;
    @NotBlank(message = "Second word is required")
    String wordTwo;
    @NotBlank(message = "A relation is required")
    RelationType relation;
}