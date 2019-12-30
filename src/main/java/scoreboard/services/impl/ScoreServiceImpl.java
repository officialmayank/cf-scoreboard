package scoreboard.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import scoreboard.exception.score.ScoreNegativeException;
import scoreboard.exception.score.TeamInActiveException;
import scoreboard.exception.score.TeamNotPresentException;
import scoreboard.model.Score;
import scoreboard.model.Team;
import scoreboard.repository.MemberRepo;
import scoreboard.repository.ScoreRepo;
import scoreboard.repository.TeamRepo;

@Service
public class ScoreServiceImpl {

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private TeamRepo teamRepo;

    @Autowired
    private ScoreRepo scoreRepo;
    
    public Score createScore(int value, long memberId) throws Exception {
    	if(value < 0) {
    		throw new ScoreNegativeException();
    	}
    	Long teamId = memberRepo.getTeamId(memberId);
    	if(teamId == null) {
    		throw new TeamNotPresentException();
    	}
    	Team team = teamRepo.getTeam(teamId);
    	if(!team.getIsActive()) {
    		throw new TeamInActiveException();
    	}
    	Score score = scoreRepo.createScore(new Score(value, memberId, teamId));
    	teamRepo.updateScoreForTeam(teamId, score.getValue());
        return score;
    }
}
