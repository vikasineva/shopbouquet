package com.ua.rosella.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "Products")
public class Bouquet extends Product {
    @Field(name = "composition.composition")
    List<BouquetFlower> composition;
    @Field(name = "themes")
    List<String> themes;
    @Field(name = "kind")
    String kind;
    @Field(name = "translitKind")
    String translitKind;
    @Field(name = "subspecies")
    String subspecies;
    @Field(name = "translitSubspecies")
    String translitSubspecies;
    public class Size{
        Double height;
        Double width;

        public Double getHeight() {
            return height;
        }

        public void setHeight(Double height) {
            this.height = height;
        }

        public Double getWidth() {
            return width;
        }

        public void setWidth(Double width) {
            this.width = width;
        }
    }
    @Field(name = "size")
    public Size size;
    @Field(name = "composition.wrapping")
    public List<String> wrapping;


    public List<BouquetFlower> getComposition() {
        return composition;
    }

    public void setComposition(List<BouquetFlower> composition) {
        this.composition = composition;
    }

    public List<String> getThemes() {
        return themes;
    }

    public void setThemes(List<String> themes) {
        this.themes = themes;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getSubspecies() {
        return subspecies;
    }

    public void setSubspecies(String subspecies) {
        this.subspecies = subspecies;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public List<String> getWrapping() {
        return wrapping;
    }

    public void setWrapping(List<String> wrapping) {
        this.wrapping = wrapping;
    }

    public String getTranslitKind() {
        return translitKind;
    }

    public void setTranslitKind(String translitKind) {
        this.translitKind = translitKind;
    }

    public String getTranslitSubspecies() {
        return translitSubspecies;
    }

    public void setTranslitSubspecies(String translitSubspecies) {
        this.translitSubspecies = translitSubspecies;
    }
}
