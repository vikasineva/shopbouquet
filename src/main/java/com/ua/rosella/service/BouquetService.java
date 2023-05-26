package com.ua.rosella.service;

import com.ua.rosella.model.Bouquet;
import com.ua.rosella.repository.BouquetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BouquetService {
    @Autowired
    BouquetRepository bouquetRepository;

    public Bouquet getBouquetByTranslit(String translitName) {
        return bouquetRepository.findByTranslitName(translitName);
    }

    public List<Bouquet> getBouquetsByKind(String kind) {
        return bouquetRepository.findAllByTranslitKind(kind);
    }

    public List<Bouquet> getBouquetsBySubspecies(String subspecies) {
        return bouquetRepository.findAllByTranslitSubspecies(subspecies);
    }
}
