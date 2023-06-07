package com.ua.rosella.model;

import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.util.Pair;

import java.util.List;

@Document(collection = "Products")
public class Bouquet extends Product {
    @Field(name = "composition.composition")
    List<BouquetFlower> composition;

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

        public static Pair<Integer,Integer> getIntervalBySizeName(String name){
            return switch (name) {
                case "S" -> Pair.of(0, 25);
                case "M" -> Pair.of(25, 35);
                case "L" -> Pair.of(35, 50);
                case "XL" -> Pair.of(50, Integer.MAX_VALUE);
                default -> Pair.of(0,0);
            };
        }
    }

    public class Theme{
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
    @Field(name="themes")
    public List<Theme> themes;

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

    public List<Theme> getThemes() {
        return themes;
    }

    public void setThemes(List<Theme> themes) {
        this.themes = themes;
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


}
