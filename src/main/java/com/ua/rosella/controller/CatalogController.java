package com.ua.rosella.controller;

import com.ua.rosella.ConstantVariables;
import com.ua.rosella.service.BouquetService;
import com.ua.rosella.util.Pagination;
import com.ua.rosella.util.SortType;
import com.ua.rosella.viewModel.CatalogViewModel;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@RestController
@CrossOrigin
public class CatalogController {

    @Autowired
    private BouquetService service;

    // OLD VERSION
   /* @GetMapping(value = "/{catalogName}/{page}", produces = "application/json")
    public ResponseEntity<?> getCatalog(@PathVariable String catalogName, @PathVariable int page){
        if(catalogName==null || catalogName.isEmpty() || page<=0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catalogName = catalogName.toLowerCase();
        var products = service.getBouquetsByKind(catalogName, page);
        int itemsCount;
        if(products==null || products.isEmpty()){
            products = service.getBouquetsBySubspecies(catalogName, page);
            itemsCount = service.getSizeBouquetsBySubspecies(catalogName);
        }else {
            itemsCount = service.getSizeBouquetsByKind(catalogName);
        }
        if(products==null || products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var pagination = new Pagination(
                page,
                itemsCount,
                ConstantVariables.itemsPerPage
        );
        var model = new CatalogViewModel(products, pagination);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }*/

    @GetMapping(value = "/{catalogName}", produces = "application/json")
    public ResponseEntity<?> getCatalog(
            @PathVariable String catalogName,
            @RequestParam(name = "tsina", required = false) String priceInterval,
            @RequestParam(name = "kvity", required = false) List<String> flowers,
            @RequestParam(name = "rozmir", required = false) List<String> sizes,
            @RequestParam(name = "colir", required = false) List<String> colors,
            @RequestParam(name = "sort", required = false) List<String> kind,
            @RequestParam(name = "pryvid", required = false) List<String> themes,
            @RequestParam(defaultValue = "novelty", required = false) String sortType,
            @RequestParam(defaultValue = "1", required = false) int page
    ) {
        if(catalogName==null || catalogName.isEmpty() || page<=0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            SortType type = SortType.checkType(sortType);
            var catalog = service.findAllByCatalogSearchQuery(catalogName,priceInterval,flowers,sizes,colors,kind,themes,type,page);
            if(catalog==null){
                return new ResponseEntity<>(new LinkedList<>(),HttpStatus.NOT_FOUND);
            }
            var pagination = new Pagination(
                    page,
                    catalog.getSecond(),
                    ConstantVariables.itemsPerPage
            );
            return ResponseEntity.ok(new CatalogViewModel(catalog.getFirst(), pagination));
        }
    }







    @GetMapping(value = "/product/{productName}", produces = "application/json")
    public ResponseEntity<?> getProduct(@PathVariable String productName){
        if(productName==null || productName.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productName = productName.toLowerCase();
        var product = service.getBouquetByTranslit(productName);
        if(product!=null){
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
