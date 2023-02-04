package io.venly.demo.repository;

import io.venly.demo.entity.RelationType;
import io.venly.demo.entity.WordRelation;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordRelationRepository extends ListCrudRepository<WordRelation, Integer> {

    List<WordRelation> findByRelation(RelationType relationType);

    Optional<WordRelation> findByWordOneAndWordTwo(String wordOne, String wordTwo);
}