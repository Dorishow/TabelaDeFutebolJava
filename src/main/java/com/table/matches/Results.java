package com.table.matches;


import lombok.Getter;
import lombok.ToString;


import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@ToString
@Getter
public class Results {
    private Set<Match> results = new TreeSet<>();

    public void add(Match match){
        results.add(match);
    }

    public List<Match> filterByTeam(String teamName){
        return results.stream()
                .filter(match -> (match.getHomeTeam().equals(teamName) || match.getAwayTeam().equals(teamName)))
                .collect(Collectors.toList());
    }
}
