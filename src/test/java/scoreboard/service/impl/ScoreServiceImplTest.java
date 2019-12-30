package scoreboard.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import scoreboard.exception.score.ScoreNegativeException;
import scoreboard.exception.score.TeamInActiveException;
import scoreboard.exception.score.TeamNotPresentException;
import scoreboard.model.Score;
import scoreboard.model.Team;
import scoreboard.repository.MemberRepo;
import scoreboard.repository.ScoreRepo;
import scoreboard.repository.TeamRepo;
import scoreboard.services.impl.ScoreServiceImpl;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ScoreServiceImplTest {

    @Mock
    ScoreRepo scoreRepo;

    @Mock
    TeamRepo teamRepo;

    @Mock
    MemberRepo memberRepo;

    @InjectMocks
    private ScoreServiceImpl scoreService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCreateScore_whenCreateScoreCalled() throws Exception {
        Mockito.when(memberRepo.getTeamId(Mockito.anyInt())).thenReturn(1L);
        Mockito.when(teamRepo.getTeam(Mockito.anyLong())).thenReturn(new Team("Team1"));
        Mockito.when(scoreRepo.createScore(Mockito.any(Score.class))).thenReturn(new Score(15,2,1));
        Mockito.doNothing().when(teamRepo).updateScoreForTeam(Mockito.anyLong(), Mockito.anyLong());
        assertThat(scoreService.createScore(15, 2).getValue(), is(15));
    }

    @Test (expected = ScoreNegativeException.class)
    public void shouldThrowScoreNegativeException_whenCreateScoreCalled() throws Exception {
        scoreService.createScore(-15, 2);
    }

    @Test (expected = TeamNotPresentException.class)
    public void shouldThrowTeamNotPresentException_whenCreateScoreCalled() throws Exception {
        Mockito.when(memberRepo.getTeamId(Mockito.anyLong())).thenReturn(null);
        scoreService.createScore(15, 2);
    }

    @Test (expected = TeamInActiveException.class)
    public void shouldThrowTeamInActiveException_whenCreateScoreCalled() throws Exception {
        Team team = new Team("Team1");
        team.setIsActive(false);
        Mockito.when(memberRepo.getTeamId(Mockito.anyLong())).thenReturn(1L);
        Mockito.when(teamRepo.getTeam(Mockito.anyLong())).thenReturn(team);
        scoreService.createScore(15, 2);
    }
}