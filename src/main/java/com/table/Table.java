package com.table;

import com.table.enums.MatchResult;
import com.table.matches.Match;
import com.table.matches.FileHandler;
import com.table.teams.Team;

import java.util.*;

public class Table {
    private static Set<String> teamsList = new HashSet<>();
    private static SortedTable sortedTable = new SortedTable();

    public static void main(String[] args) {

        FileHandler fileContent = readFromFile("santander811matchesResult.csv");
        Map<String, List<Match>> allMatchesOfEachTeam = new HashMap<>();
        teamsList.forEach(team -> allMatchesOfEachTeam.put(team, fileContent.getMatchesFromFileContent().filterByTeam(team)));
        allMatchesOfEachTeam.forEach((team, matches) -> generateFileByTeam(team, matches));
        sortedTable.table.forEach(System.out::println);
    }

    private static void generateFileByTeam(String team, List<Match> matches){
        Team newTeam = Team.builder().name(team).points(0).wins(0).draws(0).loses(0).build();
        matches.forEach(match -> {
//            Add match to the Team file
            MatchResult result = newTeam.handleMatch(match);
            if(result == MatchResult.DRAW) newTeam.addDraw();
            else if(result == MatchResult.WIN) newTeam.addWin();
            else if(result == MatchResult.LOOSE) newTeam.addLoose();
        });
        sortedTable.getTable().add(newTeam); //Add Team to the table file
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
