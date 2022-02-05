package com.table;

import com.table.services.TableServices;
import com.table.utils.DummyFile;
import java.util.Set;
import java.util.stream.Stream;

public class Table {
    public static void main(String[] args) {
        DummyFile file = new DummyFile();
        String matchesBundleString = file.getFileAsString();
        Stream<String> matchesAsStrings = matchesBundleString.lines();

        Set<String> allTeams;
        allTeams = TableServices.getTeamsFromString(matchesAsStrings);
        allTeams.forEach(System.out::println);
    }


}
