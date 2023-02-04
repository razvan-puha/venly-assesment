package io.venly.demo.service;

import io.venly.demo.dto.WordRelationDto;
import io.venly.demo.dto.WordRelationRequestBody;
import io.venly.demo.entity.RelationType;
import io.venly.demo.entity.WordRelation;
import io.venly.demo.repository.WordRelationRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WordRelationService {
    WordRelationRepository wordRelationRepository;

    public int addWordRelation(WordRelationRequestBody wordRelationDto) {
        WordRelation wordRelation = new WordRelation();
        wordRelation.setWordOne(wordRelationDto.getWordOne());
        wordRelation.setWordTwo(wordRelationDto.getWordTwo());
        wordRelation.setRelation(wordRelationDto.getRelation());

        WordRelation createdEntity = wordRelationRepository.save(wordRelation);
        return createdEntity.getId();
    }

    public List<WordRelationDto> getWordRelations() {
        return wordRelationRepository.findAll().stream()
            .map(wordRelation -> WordRelationDto.of(wordRelation.getId(),
                wordRelation.getWordOne(),
                wordRelation.getWordTwo(),
                wordRelation.getRelation(),
                false))
            .toList();
    }

    public List<WordRelationDto> getWordRelationsByRelation(RelationType relation, boolean showAlsoInverse) {
        List<WordRelationDto> resultList = new ArrayList<>(wordRelationRepository.findByRelation(relation).stream()
            .map(wordRelation -> WordRelationDto.of(wordRelation.getId(),
                wordRelation.getWordOne(),
                wordRelation.getWordTwo(),
                wordRelation.getRelation(),
                false))
            .toList());

        if (showAlsoInverse) {
            List<WordRelationDto> inversedWordRelations = resultList.stream()
                .map(wordRelationDto -> WordRelationDto.of(wordRelationDto.getId(),
                    wordRelationDto.getWordTwo(),
                    wordRelationDto.getWordOne(),
                    wordRelationDto.getRelation(),
                    true)).toList();

            resultList.addAll(inversedWordRelations);
        }

        return resultList;
    }
}