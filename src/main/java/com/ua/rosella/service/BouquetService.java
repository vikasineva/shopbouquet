package com.ua.rosella.service;

import com.ua.rosella.ConstantVariables;
import com.ua.rosella.model.Bouquet;
import com.ua.rosella.repository.BouquetRepository;
import com.ua.rosella.util.SearchUtils;
import com.ua.rosella.util.SortType;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class BouquetService {
    @Autowired
    BouquetRepository bouquetRepository;

    @Autowired
    MongoTemplate mongoTemplate;


    public Bouquet getBouquetByTranslit(String translitName) {
        return bouquetRepository.findByTranslitName(translitName);
    }

    /*public List<Bouquet> getBouquetsByKind(String kind, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, ConstantVariables.itemsPerPage);
        return bouquetRepository.findAllByTranslitKind(kind, pageable);
    }

    public List<Bouquet> getBouquetsBySubspecies(String subspecies, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, ConstantVariables.itemsPerPage);
        return bouquetRepository.findAllByTranslitSubspecies(subspecies, pageable);
    }*/
    public List<Bouquet> getAllBouquets(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, ConstantVariables.itemsPerPage);
        return bouquetRepository.findAll(pageable).getContent();
    }

    public int getSizeAllBouquets() {
        return bouquetRepository.findAll().size();
    }

    /* RETURNS PAIR: LIST OF BOUQUETS AND NUMBER OF BOUQUETS ON ALL PAGES */
    public Pair<List<Bouquet>, Integer> findAllByCatalogSearchQuery(String catalogName, String priceInterval,
                                                                    List<String> flowers, List<String> sizes,
                                                                    List<String> colors, List<String> kind,
                                                                    List<String> theme, SortType sortType,
                                                                    int page){

        List<Criteria> criteriaList = new LinkedList<>();
        if(catalogName==null){
            return null;
        }else{
            criteriaList.add(Criteria.where("categories.translitName").is(catalogName));
        }
        if(priceInterval!=null) {
            String[] split = priceInterval.split("-");
            criteriaList.add(Criteria.where("price").gte(Integer.parseInt(split[0])).lte(Integer.parseInt(split[1])));
        }
        if(sizes !=null){
            List<Pair<Integer,Integer>> sizeIntervals = new LinkedList<>();
            sizes.forEach(size ->{
                sizeIntervals.add(Bouquet.Size.getIntervalBySizeName(size));
            });
            var sizeCriteria = new Criteria();
            var tmpSizeCriteriaList = new LinkedList<Criteria>();
            for(Pair<Integer,Integer> interval:sizeIntervals){
                tmpSizeCriteriaList.add(Criteria.where("size.width").gte(interval.getFirst()).lt(interval.getSecond()));
            }
            criteriaList.add(sizeCriteria.orOperator(tmpSizeCriteriaList));
        }
        if(flowers!=null){
            flowers = SearchUtils.ParameterListToLowerCase(flowers);
            criteriaList.add(Criteria.where("composition.composition.flower.translitName").in(flowers));
        }
        if(colors!=null){
            colors = SearchUtils.ParameterListToLowerCase(colors);
            criteriaList.add(Criteria.where("composition.composition.translitColor").in(colors));
        }
        if(kind!=null){
            kind = SearchUtils.ParameterListToLowerCase(kind);
            criteriaList.add(Criteria.where("composition.composition.translitKind").in(kind));
        }
        if(theme!=null){
            theme = SearchUtils.ParameterListToLowerCase(theme);
            criteriaList.add(Criteria.where("themes.translitName").in(theme));
        }
        MatchOperation match = Aggregation.match(new Criteria().andOperator(criteriaList));
        SkipOperation skip = Aggregation.skip((long) (page - 1) *ConstantVariables.itemsPerPage);
        LimitOperation limit = Aggregation.limit(ConstantVariables.itemsPerPage);
        SortOperation sortOperation = null;
        Aggregation aggregation;
        switch (sortType){
            case CHEAP -> sortOperation = Aggregation.sort(Sort.by(Sort.Direction.ASC,"price"));
            case EXPENSIVE -> sortOperation = Aggregation.sort(Sort.by(Sort.Direction.DESC,"price"));
            case NOVELTY -> sortOperation = Aggregation.sort(Sort.by(Sort.Direction.ASC,"_id"));
            case POPULARITY -> sortOperation = Aggregation.sort(Sort.by(Sort.Direction.DESC,"count"));
        }
        if(!sortType.equals(SortType.POPULARITY)) {
            aggregation = Aggregation.newAggregation(match, sortOperation, skip, limit);
        }else {
            LookupOperation lookup = LookupOperation.newLookup()
                                    .from("Orders")
                                    .localField("_id")
                                    .foreignField("items.items.item_id")
                                    .as("matchDoc");

            AddFieldsOperation addField = Aggregation.addFields()
                    .addFieldWithValue("count",
                    Aggregation.addFields().addFieldWithValue("$size","$matchDoc")
            ).build();
            ProjectionOperation project = Aggregation.project().andExclude("count");
            aggregation = Aggregation.newAggregation(match,lookup,addField, sortOperation, skip,limit,project);
        }
        Aggregation findAndCount = Aggregation.newAggregation(match, Aggregation.count().as("total"));
        AggregationResults<Document> countResult = mongoTemplate.aggregate(findAndCount,"Products", Document.class);
        var catalogResult = mongoTemplate.aggregate(aggregation,"Products", Bouquet.class);
        var list = catalogResult.getMappedResults();
        if(list.isEmpty()){
            return null;
        }
        return Pair.of(catalogResult.getMappedResults(), Objects.requireNonNull(countResult.getUniqueMappedResult()).getInteger("total"));

    }


}
