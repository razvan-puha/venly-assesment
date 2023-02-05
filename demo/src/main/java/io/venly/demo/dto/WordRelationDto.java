package io.venly.demo.dto;


import io.venly.demo.entity.RelationType;
import lombok.Value;

@Value(staticConstructor = "of")
public class WordRelationDto {

    String wordOne;
    String wordTwo;
    RelationType relation;
    boolean inverse;
}