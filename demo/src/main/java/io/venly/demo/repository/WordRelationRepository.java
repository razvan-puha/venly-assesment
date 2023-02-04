package io.venly.demo.repository;

import io.venly.demo.entity.WordRelation;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRelationRepository extends ListCrudRepository<WordRelation, Integer> {

}