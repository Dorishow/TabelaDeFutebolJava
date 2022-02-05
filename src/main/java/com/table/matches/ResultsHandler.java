package com.table.matches;


import FileReader.Reader;
import lombok.Getter;

import java.util.List;


@Getter
public class ResultsHandler {

    private Reader reader;
    private List<String> fileText;

    public ResultsHandler(String path) {
        this.reader = new Reader(path);
        this.fileText = FileReader.ReadMatchs.readMatchs(reader);
    }

    public Results resultsHandler() {
        Results results = new Results();

        fileText.forEach(match -> {
            String[] matchData = match.split(";");
            results.add(Match.builder().homeTeam(matchData[0]).awayTeam(matchData[1]).homeTeamScore(matchData[2]).awayTeamScore(matchData[3]).matchDate(matchData[4]).build());
        });

        return results;
    }
}
