package org.example.Filtering;

import org.example.Gender;
import org.example.LtuNatIdModel;

import java.util.List;
import java.util.stream.Collectors;

public class CustomFilters {

    public static List<LtuNatIdModel> filterByGender(List<LtuNatIdModel> list, Gender gender){
        return list.stream().filter(id -> {
            if(id.getGender() == null) return false;
            return id.getGender().equals(gender);
        }).collect(Collectors.toList());
    }
}
