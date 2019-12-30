package scoreboard.repository;

import scoreboard.model.Member;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

@Repository
public class MemberRepo {

    private static final AtomicLong counter = new AtomicLong();
    private Map<Long, Member> memberMap = new HashMap<>();

    public Member createMember(Member member) {
        long id = counter.incrementAndGet();
        member.setId(id);
        addMemberToMemberMap(member);
        return member;
    }
    
    public Member getMember(long id) {
        return memberMap.get(id);
    }

    public Long getTeamId(long memberId) {
        return memberMap.get(memberId).getTeamId();
    }

    private void addMemberToMemberMap(Member member) {
        memberMap.put(member.getId(), member);
    }
    
    public Member removeTeamFromMember(long id) {
        Member member = getMember(id);
        member.setTeamId(null);
        return member;
    }
    
    public Member addTeamToMember(long id, Long teamId) {
        Member member = getMember(id);
        member.setTeamId(teamId);
        return member;
    }

}
