import com.table.Table;
import com.table.matches.FileHandler;
import com.table.matches.Match;
import com.table.utils.services.FileContentGetter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Table table = new Table();
        FileHandler fileContent = FileContentGetter.readFromFile("santander811matchesResult.csv");
        table.setTeamsList(FileContentGetter.getMatchHostsFromFile(fileContent));
        Map<String, List<Match>> allMatchesOfEachTeam = new HashMap<>();
        table.getTeamsList().forEach(team -> allMatchesOfEachTeam.put(team, fileContent.getMatchesFromFileContent().filterByTeam(team)));
        allMatchesOfEachTeam.forEach((team, matches) -> table.generateFilesByTeam(team, matches));
        table.generateTableFile();
    }
}
