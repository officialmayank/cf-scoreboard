package scoreboard.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import scoreboard.model.Team;
import scoreboard.repository.TeamRepo;
import scoreboard.services.impl.TeamServiceImpl;
import java.util.Arrays;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TeamServiceImplTest {


    @Mock
    TeamRepo teamRepo;

    @InjectMocks
    private TeamServiceImpl teamService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetTeamList_whenGetTeamListCalled() {
        Mockito.when(teamRepo.getTeamList()).thenReturn(Arrays.asList(new Team("Team1")));
        assertThat(teamService.getTeamList().size(), is(1));
    }

    @Test
    public void shouldGetTeam_whenGetTeamCalled()  {
        Mockito.when(teamRepo.getTeam(Mockito.anyLong())).thenReturn(new Team("Team1"));
        assertThat(teamService.getTeam(1L).getName(), is("Team1"));
    }

    @Test
    public void shouldCreateTeam_whenCreateTeamCalled()  {
        Team team = new Team("Team2");
        Mockito.when(teamRepo.createTeam(Mockito.any(Team.class))).thenReturn(team);
        assertThat(teamService.createTeam("Team2").getName(), is("Team2"));
    }

    @Test
    public void shouldUpdateTeamIfActive_whenUpdateTeamIsActiveCalled()  {
        Team team = new Team("Team2");
        Mockito.when(teamRepo.updateTeamIsActive(Mockito.anyLong(), Mockito.anyBoolean())).thenReturn(team);
        assertThat(teamService.updateTeamIsActive(1, true).getName(), is("Team2"));
    }
}