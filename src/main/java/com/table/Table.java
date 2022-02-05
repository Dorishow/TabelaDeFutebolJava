package com.table;

import com.table.matches.Match;
import com.table.matches.FileHandler;
import com.table.teams.Team;

import java.util.*;

public class Table {
    private static Set<String> teamsList = new HashSet<>();

    public static void main(String[] args) {

        FileHandler fileContent = readFromFile("santander811matchesResult.csv");
        List<List<Match>> allMatchesForEachTeam = new ArrayList<>();
        teamsList.forEach(team -> allMatchesForEachTeam.add(fileContent.getMatchesFromFileContent().filterByTeam(team)));
        allMatchesForEachTeam.forEach(team -> generateFileByTeam(team));


        //Calcular os pontos dos times
        //Adicionar os objetos de times no table abaixo

        SortedTable table = new SortedTable();
        table.table.add(Team.builder().name("Botafogo").points(28).wins(9).loses(0).draws(1).build());
        table.table.add(Team.builder().name("Vasco").points(23).wins(7).loses(1).draws(2).build());
        table.table.add(Team.builder().name("Ceará").points(23).wins(10).loses(0).draws(0).build());
        table.table.add(Team.builder().name("Cruzeiro").points(23).wins(9).loses(0).draws(0).build());
        table.table.add(Team.builder().name("Corinthians").points(24).wins(10).loses(0).draws(0).build());
        table.table.add(Team.builder().name("Fortaleza").points(27).wins(10).loses(0).draws(0).build());

        table.table.forEach(System.out::println);
    }



    private static void generateFileByTeam(List<Match> matches){
        
//        matches.forEach(System.out::println);
//        System.out.printf("%n---------------------------------------------------%n");
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
