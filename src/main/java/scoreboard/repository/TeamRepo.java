package scoreboard.repository;

import scoreboard.model.Team;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

@Repository
public class TeamRepo {

    private Map<Long, Team> teamMap = new HashMap<>();
    private static final AtomicLong counter = new AtomicLong();

    public Collection<Team> getTeamList(){
        return teamMap.values();
    }

    public Team getTeam(long id){
        return teamMap.get(id);
    }

    public Team createTeam(Team team) {
        long id = counter.incrementAndGet();
        team.setId(id);
        teamMap.put(id,team);
        return team;
    }
    
    public void updateScoreForTeam(long teamId, long addScore) {
        Team team = getTeam(teamId);
        Long newScore = team.getScore() + addScore;
        team.setScore(newScore);
    }
    
    public Team updateTeamIsActive(Long teamId, boolean isActive) {
        Team team = getTeam(teamId);
        team.setIsActive(isActive);
        return team;
    }
}
