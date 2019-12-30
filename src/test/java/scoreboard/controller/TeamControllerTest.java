package scoreboard.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import scoreboard.model.Team;
import scoreboard.services.impl.TeamServiceImpl;

public class TeamControllerTest {

    private MockMvc mockMvc;
    @Mock
    private TeamServiceImpl teamService;

    @InjectMocks
    private TeamController teamController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
            .standaloneSetup(teamController)
            .build();
    }
    
	@Test
	public void shouldCreateTeam_whenCreateTeam() throws Exception {
        Mockito.when(teamService.getTeam(Mockito.anyLong())).thenReturn(new Team("T1"));
		this.mockMvc.perform(get("/teams/1")).andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$.name").value("T1"));
	}
	
	@Test
	public void shouldCreateAndGetTeam_whenCreateAndGetTeam() throws Exception {
        Mockito.when(teamService.createTeam(Mockito.anyString())).thenReturn(new Team("T2"));
        this.mockMvc.perform(post("/teams?name=TeamA")).andDo(print()).andExpect(status().isOk())
        	.andExpect(jsonPath("$.name").value("T2"));
	}
	
	@Test
	public void shouldCreateTeamAndMakeTeamInactive_whenCreateTeamAndMakeTeamInactive() throws Exception {
        Team team = new Team("T3");
        team.setIsActive(false);
        Mockito.when(teamService.updateTeamIsActive(Mockito.anyLong(), Mockito.anyBoolean())).thenReturn(team);
		this.mockMvc.perform(put("/teams/1/makeInactive")).andDo(print()).andExpect(status().isOk())
			.andExpect(jsonPath("$.isActive").value(false));
	}

}
