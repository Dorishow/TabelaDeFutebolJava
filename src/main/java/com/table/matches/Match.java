package com.table.matches;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Comparator;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class Match implements Comparable<Match> {

    private String home;
    private String guest;
    private String score;
    private String date;

    @Override
    public int compareTo(Match match) {

        int compareDate = date.compareTo(match.date);
        if(compareDate != 0) {
            return compareDate;
        }

        int compareName = home.compareTo(match.home);
        if(compareName != 0){
            return compareName;
        }

        int compareAwayTeam = guest.compareTo(match.guest);
        if(compareAwayTeam != 0) {
            return compareAwayTeam;
        }
        return score.compareTo(match.score);
    }

    public int matchWinner(){
        String[] splitMatchScore = score.split("-");
        int homeTeamScore = Integer.parseInt(splitMatchScore[0]);
        int awayTeamScore = Integer.parseInt(splitMatchScore[1]);
//        System.out.println(Integer.compare(homeTeamScore, awayTeamScore));
        return Integer.compare(homeTeamScore, awayTeamScore);
    }


}