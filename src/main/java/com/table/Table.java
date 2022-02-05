package com.table;

import com.table.matches.Match;
import com.table.matches.ResultsHandler;
import com.table.teams.Team;

import java.util.*;

public class Table {
    private static Set<String> allTeams = new HashSet<>();

    public static void main(String[] args) {

        ResultsHandler results = readFromFile("santander811matchesResult.csv");
        List<List<Match>> matchesByTeam = new ArrayList<>();
        allTeams.forEach(team -> matchesByTeam.add(results.resultsHandler().filterByTeam(team)));
        matchesByTeam.forEach(team -> generateFileByTeam(team)); //trocar o sout pelo método que itera o arquivo


        //Calcular os pontos dos times
        //Adicionar os objetos de times no table abaixo

        Comparator tableComparator = Comparator
                .comparing(Team::getPoints, Comparator.reverseOrder())
                .thenComparing(Team::getWins, Comparator.reverseOrder())
                .thenComparing(Team::getDraws, Comparator.reverseOrder())
                .thenComparing(Team::getLoses);

        SortedSet<Team> table = new TreeSet<>(tableComparator);
    }



    private static void generateFileByTeam(List<Match> Matches){
        Matches.forEach(System.out::println);
        System.out.printf("%n---------------------------------------------------%n");
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

    private static ResultsHandler readFromFile(String path) {
        ResultsHandler results = new ResultsHandler(path);
        results.getFileText().forEach( match -> {
            String team = match.split(";")[0];
            allTeams.add(team);
        } );
        return results;
    }
}
