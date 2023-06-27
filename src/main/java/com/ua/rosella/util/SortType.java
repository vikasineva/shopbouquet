package com.ua.rosella.util;

public enum SortType {
    CHEAP,EXPENSIVE, NOVELTY, POPULARITY;

    public static SortType checkType(String type){
        for(SortType t :SortType.values()){
            if(t.name().toLowerCase().equals(type.toLowerCase())){
                return t;
            }
        }
        return null;
    }
}
