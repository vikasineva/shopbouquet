package com.ua.rosella.repository;

import com.ua.rosella.model.Bouquet;
import com.ua.rosella.model.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BouquetRepository extends MongoRepository<Bouquet, ObjectId> {
    Bouquet findByTranslitName(String transliteration);
    List<Bouquet> findAllByTranslitKind(String kind);
    List<Bouquet> findAllByTranslitSubspecies(String subspecies);
}
