package scoreboard.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import scoreboard.exception.member.TeamAlreadyPresentException;
import scoreboard.exception.score.TeamNotPresentException;
import scoreboard.model.Member;
import scoreboard.model.Team;
import scoreboard.repository.MemberRepo;
import scoreboard.repository.TeamRepo;
import scoreboard.services.impl.MemberServiceImpl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MemberServiceImplTest {

    @Mock
    MemberRepo memberRepo;

    @Mock
    TeamRepo teamRepo;

    @InjectMocks
    private MemberServiceImpl memberService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCreateMember_whenCreateMemberCalled() throws Exception {
        Member member = new Member("Mayank", 1L);
        Mockito.when(memberRepo.createMember(Mockito.any(Member.class))).thenReturn(member);
        Team team = new Team("TA1");
        Mockito.when(teamRepo.getTeam(1)).thenReturn(team);
        assertThat(memberService.createMember("Mayank", 1L).getName(), is("Mayank"));
    }

    @Test
    public void shouldRemoveTeamFromMember_whenRemoveTeamFromMemberCalled()  {
        Member member = new Member("Mayan", null);
        Mockito.when(memberRepo.removeTeamFromMember(1)).thenReturn(member);
        assertThat(memberService.removeTeamFromMember( 1L).getName(), is("Mayan"));
    }

    @Test
    public void shouldAddTeamToMember_whenAddTeamToMemberCalled() throws Exception {
        Member member = new Member("Maya", null);
        Mockito.when(memberRepo.getMember(1)).thenReturn(member);
        Team team = new Team("TA1");
        Mockito.when(teamRepo.getTeam(1)).thenReturn(team);
        Mockito.when(memberRepo.addTeamToMember(1, 1L)).thenReturn(member);
        assertThat(memberService.addTeamToMember( 1, 1L).getName(), is("Maya"));
    }

    @Test(expected = TeamAlreadyPresentException.class)
    public void shouldThrowTeamAlreadyPresentException_whenAddTeamToMemberCalledAndTeamPresent()
        throws TeamAlreadyPresentException, TeamNotPresentException {
        Member member = new Member("Maya", 1L);
        Team team = new Team("TA");
        Team newTeam = new Team("TB");
        Mockito.when(memberRepo.getMember(1)).thenReturn(member);
        Mockito.when(memberRepo.addTeamToMember(1, 2L)).thenReturn(member);
        Mockito.when(teamRepo.getTeam(2)).thenReturn(newTeam);
        Mockito.when(teamRepo.getTeam(1)).thenReturn(team);
        memberService.addTeamToMember( 1, 2L);
    }
}