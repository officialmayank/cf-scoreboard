package scoreboard.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import scoreboard.exception.member.TeamAlreadyPresentException;
import scoreboard.exception.member.TeamInActiveException;
import scoreboard.exception.member.TeamNotPresentException;
import scoreboard.model.Member;
import scoreboard.model.Team;
import scoreboard.repository.MemberRepo;
import scoreboard.repository.TeamRepo;

@Service
public class MemberServiceImpl {

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private TeamRepo teamRepo;

        public Member createMember(String name, Long teamId) throws TeamInActiveException, TeamNotPresentException {
        	Team team = teamRepo.getTeam(teamId);
        	if(team == null) {
        		throw new TeamNotPresentException();
        	} else if(!team.getIsActive()) {
        		throw new TeamInActiveException();
        	}
	        Member member = new Member(name, teamId);
	        return memberRepo.createMember(member);
        }

        public Member removeTeamFromMember(long id) {
        	return memberRepo.removeTeamFromMember(id);
        }

        public Member addTeamToMember(long id, Long teamId) throws TeamAlreadyPresentException, TeamNotPresentException {
	        Member member = memberRepo.getMember(id);
	        Team team = teamRepo.getTeam(teamId);
	        if(team == null) {
	        	throw new TeamNotPresentException();
	        }
	        Long oldTeamId = member.getTeamId();
	        if(oldTeamId != null && teamRepo.getTeam(oldTeamId).getIsActive()) {
	             throw new TeamAlreadyPresentException();
	        }
	        return memberRepo.addTeamToMember(id, teamId);
        }

}