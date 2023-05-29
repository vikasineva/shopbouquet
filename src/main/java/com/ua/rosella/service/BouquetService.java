package com.ua.rosella.service;

import com.ua.rosella.ConstantVariables;
import com.ua.rosella.model.Bouquet;
import com.ua.rosella.repository.BouquetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BouquetService {
    @Autowired
    BouquetRepository bouquetRepository;

    public Bouquet getBouquetByTranslit(String translitName) {
        return bouquetRepository.findByTranslitName(translitName);
    }

    public List<Bouquet> getBouquetsByKind(String kind, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, ConstantVariables.itemsPerPage);
        return bouquetRepository.findAllByTranslitKind(kind, pageable);
    }

    public List<Bouquet> getBouquetsBySubspecies(String subspecies, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, ConstantVariables.itemsPerPage);
        return bouquetRepository.findAllByTranslitSubspecies(subspecies, pageable);
    }
    public List<Bouquet> getAllBouquets(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, ConstantVariables.itemsPerPage);
        return bouquetRepository.findAll(pageable).getContent();
    }

    public int getSizeBouquetsByKind(String kind) {
        return bouquetRepository.findAllByTranslitKind(kind).size();
    }

    public int getSizeBouquetsBySubspecies(String subspecies) {
        return bouquetRepository.findAllByTranslitSubspecies(subspecies).size();
    }

    public int getSizeAllBouquets() {
        return bouquetRepository.findAll().size();
    }
}
