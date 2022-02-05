package com.table.services;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class TableServices {
    public static Set<String> getTeamsFromString(Stream<String> matchesString){
        Set<String> teams = new HashSet<>();
        matchesString.forEach( match -> {
            String home = match.split(";")[0];
            if(!teams.contains(home)) teams.add(home);
        } );
        return teams;
    }
}
