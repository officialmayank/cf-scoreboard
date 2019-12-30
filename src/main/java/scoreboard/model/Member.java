package scoreboard.model;

public class Member {
    private long id;
    private String name;
    private Long teamId;

    public Member(String name, long teamId) {
        this.name = name;
        this.teamId = teamId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getTeamId() {
        return teamId;
    }
    
    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
