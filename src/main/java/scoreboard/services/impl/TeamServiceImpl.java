package scoreboard.services.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import scoreboard.model.Team;
import scoreboard.repository.TeamRepo;

@Service
public class TeamServiceImpl {
	
    @Autowired
    private TeamRepo teamRepo;

	public Collection<Team> getTeamList() {
		return teamRepo.getTeamList();
	}
	
	public Team getTeam(long id) {
		return teamRepo.getTeam(id);
	}
	
	public Team createTeam(String name) {
        Team team = new Team(name);
        return teamRepo.createTeam(team);
	}
	
	public Team updateTeamIsActive(long id, boolean isActive) {
		return teamRepo.updateTeamIsActive(id, isActive);
	}

}
