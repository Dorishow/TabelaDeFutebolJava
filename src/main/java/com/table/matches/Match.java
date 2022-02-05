package com.table.matches;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class Match implements Comparable<Match> {

    private String homeTeam;
    private String awayTeam;
    private String homeTeamScore;
    private String awayTeamScore;
    private String matchDate;

    @Override
    public int compareTo(Match match) {
        int compareName = homeTeam.compareTo(match.homeTeam);
        if(compareName != 0){
            return compareName;
        }

        int compareDate = matchDate.compareTo(match.matchDate);
        if(compareDate != 0) {
            return compareDate;
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
