package ch01.ts;

import java.util.List;

import teamsC.Player;
import teamsC.Team;
import teamsC.Teams;
import teamsC.TeamsService;

public class TeamClient {

	public static void main(String[] args) {
		TeamsService service = new TeamsService();

		Teams port = service.getTeamsPort();

		List<Team> teams = port.getTeams();

		for(Team t : teams) {
			System.out.println("Team name: "+ t.getName() + " (roaster count: "+ t.getRoasterCount() +")");

			for(Player p : t.getPlayers()) {
				System.out.println(" Player: "+ p.getNickname());
			}
		}
	}
}
