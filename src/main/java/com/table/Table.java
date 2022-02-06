package com.table;

import com.table.matches.Match;
import com.table.teams.Team;
import com.table.utils.filesGenerator.FilesWriter;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Table {
    private Set<String> teamsList = new HashSet<>();
    private SortedTable sortedTable = new SortedTable();

    public void generateTableFile() {
        final String[] sortedTableFileContent = {""};
        sortedTable.getTable().forEach(team -> sortedTableFileContent[0] += team + "\n");
        FilesWriter.Write("src/main/files/table/table.csv", sortedTableFileContent[0]);
    }

    public void generateFilesByTeam(String team, List<Match> matches){
        Team newTeam = Team.builder().name(team).points(0).wins(0).draws(0).loses(0).build();
        final String[] fileContent = {""};
        matches.forEach(match -> {
            fileContent[0] += match + "\n";
            newTeam.handleResult(match);
        });
        FilesWriter.Write("src/main/files/teams/".concat(team).concat(".csv"), fileContent[0]);
        sortedTable.table.add(newTeam);
    }


}
