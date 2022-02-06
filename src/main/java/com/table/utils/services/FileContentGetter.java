package com.table.utils.services;

import com.table.matches.FileHandler;

import java.util.HashSet;
import java.util.Set;

public class FileContentGetter {
    public static FileHandler readFromFile(String path) {
        com.table.matches.FileHandler results = new com.table.matches.FileHandler(path);
        return results;
    }

    public static Set<String> getMatchHostsFromFile(com.table.matches.FileHandler results) {
        Set<String> teamsList = new HashSet<>();
        results.getFileText().forEach(match -> {
            String team = match.split(";")[0];
            teamsList.add(team);
        } );
        return teamsList;
    }
}
