package com.table;

import com.table.enums.MatchResult;
import com.table.matches.Match;
import com.table.matches.FileHandler;
import com.table.teams.Team;
import com.table.utils.writeFiles.FilesWriter;

import java.util.*;

public class Table {
    private static Set<String> teamsList = new HashSet<>();
    private static SortedTable sortedTable = new SortedTable();

    public static void main(String[] args) {

        FileHandler fileContent = readFromFile("santander811matchesResult.csv");
        Map<String, List<Match>> allMatchesOfEachTeam = new HashMap<>();
        teamsList.forEach(team -> allMatchesOfEachTeam.put(team, fileContent.getMatchesFromFileContent().filterByTeam(team)));
        allMatchesOfEachTeam.forEach((team, matches) -> generateFileByTeam(team, matches));
//        System.out.println("---------------------------------");
//        sortedTable.table.forEach(System.out::println);
//        System.out.println("---------------------------------");
    }

    private static void generateFileByTeam(String team, List<Match> matches){
        Team newTeam = Team.builder().name(team).points(0).wins(0).draws(0).loses(0).build();
        final String[] fileContent = {""};
        matches.forEach(match -> {
            fileContent[0] += match + "\n\r";
            MatchResult result = newTeam.handleMatch(match);
            if(result == MatchResult.DRAW) newTeam.addDraw();
            else if(result == MatchResult.WIN) newTeam.addWin();
            else if(result == MatchResult.LOOSE) newTeam.addLoose();
        });
        FilesWriter.Write("src/main/files/".concat(team).concat(".csv"), fileContent[0]);
        System.out.println(newTeam);
        sortedTable.table.add(newTeam);
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
