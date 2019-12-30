package scoreboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import scoreboard.model.Team;
import scoreboard.services.impl.TeamServiceImpl;

import java.util.Collection;

@RestController
public class TeamController {
	
    @Autowired
    private TeamServiceImpl teamServiceImpl;
    
    @RequestMapping("/teams")
    public Collection<Team> getTeamList() {
        return teamServiceImpl.getTeamList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/teams/{id}")
    public Team getTeam(
    		@PathVariable(value="id") String id
    	) {
        return teamServiceImpl.getTeam(Long.parseLong(id));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/teams")
    public Team createTeam(
    		@RequestParam(value="name") String name
    	) {
        return teamServiceImpl.createTeam(name);
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "/teams/{id}/makeInactive")
    public Team makeTeamInActive(
    		@PathVariable(value="id") String id
    	) {
    	return teamServiceImpl.updateTeamIsActive(Long.parseLong(id), false);
    }
}
