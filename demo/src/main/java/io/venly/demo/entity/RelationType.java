package io.venly.demo.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.venly.demo.exception.BadRequestException;

import java.util.stream.Stream;

public enum RelationType {
    SYNONYM,
    ANTONYM,
    RELATED;

    @JsonCreator
    public static RelationType from(String relation) {
        return Stream.of(values()).filter(item -> item.toString().compareToIgnoreCase(relation) == 0).findFirst().orElseThrow(BadRequestException::new);
    }
}