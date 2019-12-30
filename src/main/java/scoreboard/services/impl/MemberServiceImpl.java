package scoreboard.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import scoreboard.exception.member.TeamAlreadyPresentException;
import scoreboard.exception.member.TeamInActiveException;
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

        public Member createMember(String name, Long teamId) throws TeamInActiveException {
        	Team team = teamRepo.getTeam(teamId);
        	if(team != null && !team.getIsActive()) {
        		throw new TeamInActiveException();
        	}
	        Member member = new Member(name, teamId);
	        return memberRepo.createMember(member);
        }

        public Member removeTeamFromMember(long id) {
        	return memberRepo.removeTeamFromMember(id);
        }

        public Member addTeamToMember(long id, Long teamId) throws TeamAlreadyPresentException {
	        Member member = memberRepo.getMember(id);
	        Long oldTeamId = member.getTeamId();
	        if(oldTeamId != null && teamRepo.getTeam(oldTeamId).getIsActive()) {
	             throw new TeamAlreadyPresentException();
	        }
	        return memberRepo.addTeamToMember(id, teamId);
        }

}