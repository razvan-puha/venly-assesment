package io.venly.demo.service;

import io.venly.demo.dto.WordRelationDto;
import io.venly.demo.dto.WordRelationRequestBody;
import io.venly.demo.entity.RelationType;
import io.venly.demo.entity.WordRelation;
import io.venly.demo.exception.RelationAlreadyPresentException;
import io.venly.demo.repository.WordRelationRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WordRelationService {
    WordRelationRepository wordRelationRepository;

    public int addWordRelation(WordRelationRequestBody wordRelationDto) {
        Optional<WordRelation> existingRelationOptional =
            wordRelationRepository.findByWordOneAndWordTwo(wordRelationDto.getWordOne().toLowerCase().trim(),
                wordRelationDto.getWordTwo().toLowerCase().trim());

        Optional<WordRelation> inverseRelationOptional =
            wordRelationRepository.findByWordOneAndWordTwo(wordRelationDto.getWordTwo().toLowerCase().trim(),
                wordRelationDto.getWordOne().toLowerCase().trim());

        if (existingRelationOptional.isPresent()) {
            throw new RelationAlreadyPresentException(wordRelationDto.getWordOne().toLowerCase().trim(),
                wordRelationDto.getWordTwo().toLowerCase().trim());
        }
        if (inverseRelationOptional.isPresent()) {
            throw new RelationAlreadyPresentException(wordRelationDto.getWordTwo().toLowerCase().trim(),
                wordRelationDto.getWordOne().toLowerCase().trim());
        }

        WordRelation wordRelation = new WordRelation();
        wordRelation.setWordOne(wordRelationDto.getWordOne().toLowerCase().trim());
        wordRelation.setWordTwo(wordRelationDto.getWordTwo().toLowerCase().trim());
        wordRelation.setRelation(RelationType.from(wordRelationDto.getRelation()));

        WordRelation createdEntity = wordRelationRepository.save(wordRelation);
        return createdEntity.getId();
    }

    public List<WordRelationDto> getWordRelations() {
        return wordRelationRepository.findAll().stream()
            .map(wordRelation -> WordRelationDto.of(
                wordRelation.getWordOne(),
                wordRelation.getWordTwo(),
                wordRelation.getRelation(),
                false))
            .toList();
    }

    public List<WordRelationDto> getWordRelationsByRelation(RelationType relation, boolean showAlsoInverse) {
        List<WordRelationDto> resultList = new ArrayList<>(wordRelationRepository.findByRelation(relation).stream()
            .map(wordRelation -> WordRelationDto.of(
                wordRelation.getWordOne(),
                wordRelation.getWordTwo(),
                wordRelation.getRelation(),
                false))
            .toList());

        if (showAlsoInverse) {
            List<WordRelationDto> inversedWordRelations = resultList.stream()
                .map(wordRelationDto -> WordRelationDto.of(
                    wordRelationDto.getWordTwo(),
                    wordRelationDto.getWordOne(),
                    wordRelationDto.getRelation(),
                    true)).toList();

            resultList.addAll(inversedWordRelations);
        }

        return resultList;
    }

    /*
        Since I didn't have any more time during the assessment, I will try to present the solution:
            We have several options in which we can solve this problem:
            - we use a recursive algorithm in which we'll try to find the first path which goes from source to target
            - we use all the relations from the DB to build an oriented graph and find the shortest path from source to target

        I will try to implement a solution after the assessment.
     */
    @Transactional
    public String getPath(String source, String target) {
        String resultPath = "";
        return resultPath;
    }
}