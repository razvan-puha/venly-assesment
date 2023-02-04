package io.venly.demo.dto;


import io.venly.demo.entity.RelationType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Value(staticConstructor = "of")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WordRelationDto {

    int id;
    String wordOne;
    String wordTwo;
    RelationType relation;
}