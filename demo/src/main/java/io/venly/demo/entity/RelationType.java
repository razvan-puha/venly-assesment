package io.venly.demo.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.venly.demo.exception.BadRequestException;

import java.util.stream.Stream;

public enum RelationType {
    SYNONYM,
    ANTONYM,
    RELATED;

    @JsonCreator
    public static RelationType from(String relation) {
        return Stream.of(values()).filter(item -> item.toString().compareToIgnoreCase(relation.trim()) == 0).findFirst().orElseThrow(BadRequestException::new);
    }

    @JsonValue
    public String getValue() {
        return name().toLowerCase().trim();
    }
}