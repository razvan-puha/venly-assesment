package io.venly.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity(name = "word_relation")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WordRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "word_one", nullable = false)
    String wordOne;

    @Column(name = "word_two", nullable = false)
    String wordTwo;

    @Column(name = "relation", nullable = false)
    @Enumerated(value = EnumType.STRING)
    RelationType relation;
}