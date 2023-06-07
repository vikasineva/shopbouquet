package com.ua.rosella.controller;

import com.ua.rosella.ConstantVariables;
import com.ua.rosella.model.Bouquet;
import com.ua.rosella.service.BouquetService;
import com.ua.rosella.util.Pagination;
import com.ua.rosella.viewModel.CatalogViewModel;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class CatalogController {

    @Autowired
    private BouquetService service;

    @GetMapping(value = "/{catalogName}/{page}", produces = "application/json")
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
