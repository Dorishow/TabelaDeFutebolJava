package com.table;

import com.table.services.TableServices;
import com.table.teams.Team;
import com.table.utils.DummyFile;

import java.util.*;
import java.util.stream.Stream;

public class Table {
    public static void main(String[] args) {
        DummyFile file = new DummyFile();
        String matchesBundleString = file.getFileAsString();
        Stream<String> matchesAsStrings = matchesBundleString.lines();

        Set<String> allTeams;
        allTeams = TableServices.getTeamsFromString(matchesAsStrings);

        Set<Team> table = new TreeSet<>(Comparator.comparing(Team::getPoints, Comparator.reverseOrder()).thenComparing(Team::getWins, Comparator.reverseOrder()));
        table.add(Team.builder().name("Botafogo").points(10).draws(3).loses(1).wins(10).build());
        table.add(Team.builder().name("Flamengo").points(7).draws(4).loses(3).wins(8).build());
        table.add(Team.builder().name("Vasco").points(12).draws(5).loses(6).wins(7).build());
        table.add(Team.builder().name("Santos").points(12).draws(5).loses(6).wins(4).build());
        table.add(Team.builder().name("CRB").points(12).draws(5).loses(6).wins(2).build());
        table.add(Team.builder().name("Cruzeiro").points(3).draws(9).loses(7).wins(5).build());
        table.add(Team.builder().name("Paraná").points(5).draws(7).loses(8).wins(2).build());

        table.forEach(System.out::println);
    }


}
