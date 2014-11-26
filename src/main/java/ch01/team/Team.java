package ch01.team;

import java.util.List;

public class Team {
	private List<Player> players;
	private String name;

	public Team(){
		
	}

	public Team(String name, List<Player> players) {
		setName(name);
		setPlayers(players);
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRoasterCount(int n) {}

	public int getRoasterCount() {
		return (players == null) ? 0 : players.size();
	}
}
