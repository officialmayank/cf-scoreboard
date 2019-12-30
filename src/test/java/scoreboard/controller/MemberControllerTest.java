package scoreboard.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
import scoreboard.model.Member;
import scoreboard.services.impl.MemberServiceImpl;

public class MemberControllerTest {

    private MockMvc mockMvc;
    
    @Mock
    private MemberServiceImpl memberService;

    @InjectMocks
    private MemberController memberController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
            .standaloneSetup(memberController)
            .build();
    }
    
	@Test
	public void shouldCreateMember_whenCreateMemberCalled() throws Exception {

        Mockito.when(memberService.createMember(Mockito.anyString(), Mockito.anyLong())).thenReturn(new Member("MA1", 1));
        this.mockMvc.perform(post("/members?name=MA1&teamId=1")).andDo(print()).andExpect(status().isOk())
			.andExpect(jsonPath("$.id").isNumber()).andExpect(jsonPath("$.name").value("MA1"));
	}
	
	@Test
	public void shouldRemoveTeamFromMember_whenRemoveTeamFromMember() throws Exception {
        Mockito.when(memberService.removeTeamFromMember(Mockito.anyLong())).thenReturn(new Member("MA2", 2));
		this.mockMvc.perform(delete("/teams/1/members/1")).andDo(print()).andExpect(status().isOk())
			.andExpect(jsonPath("$.id").isNumber()).andExpect(jsonPath("$.name").value("MA2"));
	}
	
	@Test
	public void shouldAddTeamToMember_whenAddTeamToMember() throws Exception {
        Mockito.when(memberService.addTeamToMember(Mockito.anyLong(), Mockito.anyLong())).thenReturn(new Member("MA3", 3));

		this.mockMvc.perform(put("/teams/2/members/1")).andDo(print()).andExpect(status().isOk())
			.andExpect(jsonPath("$.id").isNumber()).andExpect(jsonPath("$.name").value("MA3"))
			.andExpect(jsonPath("$.teamId").value(3));
	}
}
