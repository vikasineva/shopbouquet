package com.ua.rosella.repository;

import com.ua.rosella.model.Bouquet;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BouquetRepository extends MongoRepository<Bouquet, ObjectId> {
    Bouquet findByTranslitName(String transliteration);
    /* OLD VERSION
    List<Bouquet> findAllByTranslitKind(String kind, Pageable pageable);
    List<Bouquet> findAllByTranslitSubspecies(String subspecies, Pageable pageable);
    List<Bouquet> findAllByTranslitKind(String kind);
    List<Bouquet> findAllByTranslitSubspecies(String subspecies);
    */

}
