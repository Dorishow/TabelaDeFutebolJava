package com.table;

import com.table.enums.MatchResult;
import com.table.matches.Match;
import com.table.matches.FileHandler;
import com.table.teams.Team;
import com.table.utils.services.FileContentGetter;
import com.table.utils.filesGenerator.FilesWriter;

import java.util.*;

public class Table {
    private static Set<String> teamsList = new HashSet<>();
    private static SortedTable sortedTable = new SortedTable();

    public static void main(String[] args) {

        FileHandler fileContent = FileContentGetter.readFromFile("santander811matchesResult.csv");
        teamsList = FileContentGetter.getMatchHostsFromFile(fileContent);
        Map<String, List<Match>> allMatchesOfEachTeam = new HashMap<>();
        teamsList.forEach(team -> allMatchesOfEachTeam.put(team, fileContent.getMatchesFromFileContent().filterByTeam(team)));
        allMatchesOfEachTeam.forEach((team, matches) -> generateFilesByTeam(team, matches));
        generateTableFile();
    }

    private static void generateTableFile() {
        final String[] sortedTableFileContent = {""};
        sortedTable.getTable().forEach(team -> sortedTableFileContent[0] += team + "\n");
        FilesWriter.Write("src/main/files/table/table.csv", sortedTableFileContent[0]);
    }

    private static void generateFilesByTeam(String team, List<Match> matches){
        Team newTeam = Team.builder().name(team).points(0).wins(0).draws(0).loses(0).build();
        final String[] fileContent = {""};
        matches.forEach(match -> {
            fileContent[0] += match + "\n";
            MatchResult result = newTeam.handleMatch(match);
            if(result == MatchResult.DRAW) newTeam.addDraw();
            else if(result == MatchResult.WIN) newTeam.addWin();
            else if(result == MatchResult.LOOSE) newTeam.addLoose();
        });
        FilesWriter.Write("src/main/files/teams/".concat(team).concat(".csv"), fileContent[0]);
        sortedTable.table.add(newTeam);
    }


}
