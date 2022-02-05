package com.table.services;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class TableServices {
    public static Set<String> getTeamsFromString(Stream<String> matchesString){
        Set<String> allTeams = new HashSet<>();
        matchesString.forEach(
                match -> {
                    String home = match.split(";")[0];
                    if(!allTeams.contains(home)) allTeams.add(home);
                }
        );
        return allTeams;
    }
}
