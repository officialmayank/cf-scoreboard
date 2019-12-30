package scoreboard.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import scoreboard.model.Score;
import scoreboard.services.impl.ScoreServiceImpl;

public class ScoreControllerTest {

    private MockMvc mockMvc;
    @Mock
    private ScoreServiceImpl scoreService;

    @InjectMocks
    private ScoreController scoreController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
            .standaloneSetup(scoreController)
            .build();
    }
    
	@Test
	public void shouldCreateMemberScore_whenCreateMemberScore() throws Exception {
        Mockito.when(scoreService.createScore(Mockito.anyInt(), Mockito.anyLong())).thenReturn(new Score(15, 1, 2));
		this.mockMvc.perform(post("/scores?value=15&memberId=1")).andExpect(status().isOk())
			.andExpect(jsonPath("$.memberId").value(1)).andExpect(jsonPath("$.value").value(15));
	}
	
	@Test(expected = Exception.class)
	public void shouldThrowException_whenCreateMemberScoreCalledAndNegativeScorePassed() throws Exception {
        Mockito.when(scoreService.createScore(Mockito.anyInt(), Mockito.anyLong())).thenThrow(new Exception());
		this.mockMvc.perform(post("/scores?value=-15&memberId=1"));
	}
	
	@Test(expected = Exception.class)
	public void shouldThrowException_whenCreateMemberScoreWithoutTeam() throws Exception {
        Mockito.when(scoreService.createScore(Mockito.anyInt(), Mockito.anyLong())).thenThrow(Exception.class);
		this.mockMvc.perform(post("/scores?value=5&memberId=1"));
	}
	
	@Test(expected = Exception.class)
	public void shouldThrowException_whenCreateMemberScoreWithInactiveTeam() throws Exception {
        Mockito.when(scoreService.createScore(Mockito.anyInt(), Mockito.anyLong())).thenThrow(Exception.class);
		this.mockMvc.perform(post("/scores?value=5&memberId=1"));
	}
}
