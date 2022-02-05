package com.table;

import com.table.matches.ResultsHandler;
import com.table.services.TableServices;
import com.table.teams.Team;
import com.table.utils.DummyFile;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Stream;

public class Table {
    public static void main(String[] args) {
        DummyFile file = new DummyFile();
        String matchesBundleString = file.getFileAsString();
        Stream<String> matchesAsStrings = matchesBundleString.lines();

        Set<String> allTeams;
        allTeams = TableServices.getTeamsFromString(matchesAsStrings);

        ResultsHandler results = new ResultsHandler("santander811matchesResult.csv");
        results.resultsHandler().getResults().forEach(System.out::println);


        Comparator tableComparator = Comparator
                .comparing(Team::getPoints, Comparator.reverseOrder())
                .thenComparing(Team::getWins, Comparator.reverseOrder())
                .thenComparing(Team::getDraws, Comparator.reverseOrder())
                .thenComparing(Team::getLoses);

        SortedSet<Team> table = generateTable(tableComparator);
//        table.forEach(System.out::println);
    }

    private static SortedSet<Team> generateTable(Comparator tableComparator) {
        SortedSet<Team> table = new TreeSet<>(tableComparator);

        table.add(Team.builder().name("Botafogo").points(28).wins(9).loses(0).draws(1).build());
        table.add(Team.builder().name("Vasco").points(23).wins(7).loses(1).draws(2).build());
        table.add(Team.builder().name("Ceará").points(23).wins(10).loses(0).draws(0).build());
        table.add(Team.builder().name("Cruzeiro").points(23).wins(9).loses(0).draws(0).build());
        table.add(Team.builder().name("Corinthians").points(24).wins(10).loses(0).draws(0).build());
        table.add(Team.builder().name("Fortaleza").points(27).wins(10).loses(0).draws(0).build());
        return table;
    }
}
