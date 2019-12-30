package scoreboard.model;

public class Score {
    private long id;
    private int value;
    private long memberId;
    private long teamId;

    public Score(int value, long memberId, long teamId) {
        this.value = value;
        this.memberId = memberId;
        this.teamId = teamId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public int getValue() {
        return value;
    }

    public long getMemberId() {
        return memberId;
    }
}
