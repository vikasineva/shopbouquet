package com.ua.rosella.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "Products")
public class Product {
    @Id
    ObjectId id;
    @Field(name = "name")
    String name;
    @Field(name = "translitName")
    String translitName;
    @Field(name = "description")
    String description;
    @Field(name = "price")
    Double price;
    @Field(name = "discount")
    Double discount;
    @JsonIgnore
    @Field(name = "picture")
    String picture;
    @Field(name = "available")
    Integer available;

    class Category{
        String name;
        String translitName;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTranslitName() {
            return translitName;
        }

        public void setTranslitName(String translitName) {
            this.translitName = translitName;
        }

    }
    @Field(name = "categories")

    List<Category> categories;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public String getTranslitName() {
        return translitName;
    }

    public void setTranslitName(String translitName) {
        this.translitName = translitName;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
