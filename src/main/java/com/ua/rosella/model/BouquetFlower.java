package com.ua.rosella.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Products")
public class BouquetFlower {
    @DBRef
    @Field(name = "flower")
    Flower flower;
    @Field(name = "amount")
    Integer quantity;
    @Field(name = "color")
    String color;

    @Field(name="translitColor")
    String translitColor;

    public String getTranslitColor() {
        return translitColor;
    }

    public void setTranslitColor(String translitColor) {
        this.translitColor = translitColor;
    }

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
