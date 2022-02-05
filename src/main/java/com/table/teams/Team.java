package com.table.teams;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class Team {
    private String name;
    private Integer draws = 0;
    private Integer wins = 0;
    private Integer loses = 0;
    private Integer points = 0;

    public void addWin() {
        if(this.wins == null) this.setWins(1);
        else this.setWins(this.wins + 1);
    }

    public void addLoose() {
        if (this.loses == null) this.setDraws(1);
        else this.setLoses(this.loses + 1);
    }

    public void addDrawn() {
        if (this.draws == null) this.setDraws(1);
        else this.setDraws(this.draws + 1);
    }
}
