package scoreboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import scoreboard.exception.member.TeamInActiveException;
import scoreboard.model.Member;
import scoreboard.services.impl.MemberServiceImpl;

@RestController
class MemberController {
	
    @Autowired
    private MemberServiceImpl memberServiceImpl;
    
    @RequestMapping(method = RequestMethod.POST, value = "/members")
    public Member createMember(
    		@RequestParam(value="name") String name, @RequestParam(value="teamId") long teamId
    	) throws TeamInActiveException {
        return memberServiceImpl.createMember(name, teamId);
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "/teams/{teamId}/members/{id}")
    public Member removeTeamFromMember(
    		@PathVariable(value="id") long id,
    		@PathVariable(value="teamId") long teamId
    	) {
    	return memberServiceImpl.removeTeamFromMember(id);
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "/teams/{teamId}/members/{id}")
    public Member addTeamToMember(
    		@PathVariable(value="id") long id,
    		@PathVariable(value="teamId") Long teamId
    	) throws Exception {
        return memberServiceImpl.addTeamToMember(id, teamId);
    }
}
