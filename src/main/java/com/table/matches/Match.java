package com.table.matches;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class Match implements Comparable<Match> {
    @Override
    public String toString() {
        return
            matchDate + ';' +
            homeTeam + ';' +
            homeTeamScore + ';' +
            awayTeamScore + ';' +
            awayTeam + ';';
    }

    private String homeTeam;
    private String awayTeam;
    private String homeTeamScore;
    private String awayTeamScore;
    private String matchDate;

    @Override
    public int compareTo(Match match) {

        int compareDate = matchDate.compareTo(match.matchDate);
        if(compareDate != 0) {
            return compareDate;
        }

        int compareName = homeTeam.compareTo(match.homeTeam);
        if(compareName != 0){
            return compareName;
        }

        int compareAwayTeam = awayTeam.compareTo(match.awayTeam);
        if(compareAwayTeam != 0) {
            return compareAwayTeam;
        }
        int compareAwayTeamScore = awayTeamScore.compareTo(match.awayTeamScore);
        if(compareAwayTeam != 0) {
            return compareAwayTeamScore;
        }
         return homeTeamScore.compareTo(match.homeTeamScore);
    }

    public int matchWinner(){
        return homeTeamScore.compareTo(awayTeamScore);
    }


}
