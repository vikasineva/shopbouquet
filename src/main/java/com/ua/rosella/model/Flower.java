package com.ua.rosella.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;
@Document(collection = "Flowers")
public class Flower {
    @Id
    ObjectId id;
    @Field(name = "name")
    String name;
    @Field(name = "description")
    String description;

    @JsonIgnore
    @Field(name = "icon")
    String icon;

    public class Color{
        String name;
        String translateName;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTranslateName() {
            return translateName;
        }

        public void setTranslateName(String translateName) {
            this.translateName = translateName;
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
}
