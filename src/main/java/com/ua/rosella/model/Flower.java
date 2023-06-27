package com.ua.rosella.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
@Document(collection = "Flowers")
public class Flower {
    @Id
    ObjectId id;

    @Field(name = "name")
    String name;

    @Field(name = "description")
    String description;

    @Field(name = "icon")
    String icon;

    @Field(name = "translitName")
    String translitName;

    @Field(name = "kind")
    String kind;

    @Field(name = "translitKind")
    String translitKind;

    public class Color{
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

    @Field(name = "colors")
    List<Color> colors;

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

    public List<Color> getColors() {
        return colors;
    }

    public void setColors(List<Color> colors) {
        this.colors = colors;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTranslitName() {
        return translitName;
    }

    public void setTranslitName(String translitName) {
        this.translitName = translitName;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getTranslitKind() {
        return translitKind;
    }

    public void setTranslitKind(String translitKind) {
        this.translitKind = translitKind;
    }
}
