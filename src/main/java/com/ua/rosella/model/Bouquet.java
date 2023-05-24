package com.ua.rosella.model;

import java.util.List;

public class Bouquet extends Good{

    List<BouquetFlower> composition;
    List<String> themes;
    String kind;
    String subspecies;
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
    public Size size;

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
}
