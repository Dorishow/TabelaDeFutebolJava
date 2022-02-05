package com.table;

import com.table.teams.Team;
import lombok.Getter;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

@Getter
public class SortedTable {

    Comparator tableComparator = Comparator
            .comparing(Team::getPoints, Comparator.reverseOrder())
            .thenComparing(Team::getWins, Comparator.reverseOrder())
            .thenComparing(Team::getDraws, Comparator.reverseOrder())
            .thenComparing(Team::getLoses);

    SortedSet<Team> table = new TreeSet<>(tableComparator);

}
