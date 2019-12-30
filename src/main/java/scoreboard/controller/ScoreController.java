package scoreboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import scoreboard.model.Score;
import scoreboard.services.impl.ScoreServiceImpl;

@RestController
public class ScoreController {
    
    @Autowired
    private ScoreServiceImpl scoreServiceImpl;
    
    @RequestMapping(method = RequestMethod.POST, value = "/scores")
    public Score createScore(
            @RequestParam(value="value") int value,
            @RequestParam(value="memberId") long memberId
    	) throws Exception {
    	return scoreServiceImpl.createScore(value, memberId);
    }
}
