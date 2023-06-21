package com.ua.rosella.util;

import java.util.LinkedList;
import java.util.List;

public class SearchUtils {

    public static List<String> ParameterListToLowerCase(List<String> list){
        List<String> result = new LinkedList<>();
        for(String parameter : list){
            result.add(parameter.toLowerCase());
        }
        return result;
    }
}
