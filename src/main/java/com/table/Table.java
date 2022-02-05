package com.table;

import com.table.enums.MatchResult;
import com.table.matches.Match;
import com.table.matches.FileHandler;
import com.table.teams.Team;

import java.util.*;

public class Table {
    private static Set<String> teamsList = new HashSet<>();

    public static void main(String[] args) {

        FileHandler fileContent = readFromFile("santander811matchesResult.csv");
        Map<String, List<Match>> allMatchesOfEachTeam = new HashMap<>();
        teamsList.forEach(team -> allMatchesOfEachTeam.put(team, fileContent.getMatchesFromFileContent().filterByTeam(team)));

        allMatchesOfEachTeam.forEach((team, matches) -> generateFileByTeam(team, matches));


        //Calcular os pontos dos times
        //Adicionar os objetos de times no sortedTable abaixo

        SortedTable sortedTable = new SortedTable();
        sortedTable.table.add(Team.builder().name("Botafogo").points(28).wins(9).loses(0).draws(1).build());
        sortedTable.table.add(Team.builder().name("Vasco").points(23).wins(7).loses(1).draws(2).build());
        sortedTable.table.add(Team.builder().name("Ceará").points(23).wins(10).loses(0).draws(0).build());
        sortedTable.table.add(Team.builder().name("Cruzeiro").points(23).wins(9).loses(0).draws(0).build());
        sortedTable.table.add(Team.builder().name("Corinthians").points(24).wins(10).loses(0).draws(0).build());
        sortedTable.table.add(Team.builder().name("Fortaleza").points(27).wins(10).loses(0).draws(0).build());

//        sortedTable.table.forEach(System.out::println);
    }



    private static void generateFileByTeam(String team, List<Match> matches){
        Team newTeam = Team.builder().name(team).points(0).wins(0).draws(0).loses(0).build();
//        System.out.println(team);
        matches.forEach(match -> {
//            System.out.println(match);
            MatchResult result = newTeam.handleMatch(match);
            if(result == MatchResult.DRAW) newTeam.addDraw();
            else if(result == MatchResult.WIN) newTeam.addWin();
            else if(result == MatchResult.LOOSE) newTeam.addLoose();
        });
        System.out.println(newTeam);
    }

    private static FileHandler readFromFile(String path) {
        FileHandler results = new FileHandler(path);
        updateTeamsList(results);
        return results;
    }

    private static void updateTeamsList(FileHandler results) {
        results.getFileText().forEach(match -> {
            String team = match.split(";")[0];
            teamsList.add(team);
        } );
    }
}
