package com.table.teams;

import com.table.enums.MatchResult;
import com.table.matches.Match;
import lombok.*;

//@ToString
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Team {
    private String name;
    private Integer draws = 0;
    private Integer wins = 0;
    private Integer loses = 0;
    private Integer points = 0;

    public void addWin() {
        if(this.wins == null)  this.setWins(1);
        else this.setWins(this.wins + 1);
        this.updatePoints(3);
    }

    public void addLoose() {
        if (this.loses == null) this.setLoses(1);
        else this.setLoses(this.loses + 1);
    }

    public void addDraw() {
        if (this.draws == null)  this.setDraws(1);
        else this.setDraws(this.draws + 1);
        this.updatePoints(1);
    }

    public void updatePoints(int points){
        this.points += points;
    }

    public MatchResult handleMatch(Match match){
        MatchResult result;
        int homeScore = Integer.parseInt(match.getHomeTeamScore());
        int guestScore = Integer.parseInt(match.getAwayTeamScore());
        if(homeScore == guestScore){
           result = MatchResult.DRAW;
        } else {
            if((match.getHomeTeam().equalsIgnoreCase(this.name)) && (homeScore > guestScore))
                result = MatchResult.WIN;
            else if((match.getAwayTeam().equalsIgnoreCase(this.name)) && (guestScore > homeScore))
                result = MatchResult.WIN;
            else result = MatchResult.LOOSE;
        }
        return result;
    }

    @Override
    public String toString() {
        return  name + ';' +
                points + ';' +
                wins  + ';' +
                draws + ';' +
                loses  + ';';

    }
}
