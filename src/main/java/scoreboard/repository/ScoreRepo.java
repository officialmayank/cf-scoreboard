package scoreboard.repository;

import scoreboard.model.Score;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ScoreRepo {

    @Autowired
    MemberRepo memberRepo;
    
    private static final AtomicLong counter = new AtomicLong();
    private Map<Long, Score> scoreMap = new HashMap<>();

    public Score createScore(Score score) {
        long id = counter.incrementAndGet();
        score.setId(id);
        addScoreToScoreMap(score);
        return score;
    }

    private void addScoreToScoreMap(Score score) {
        scoreMap.put(score.getId(), score);
    }

}