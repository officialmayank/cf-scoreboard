package scoreboard.model;

public class Team {
    private Long id;
    private String name;
    private Long score;
    private boolean isActive;

    public Team(String name) {
        this.name = name;
        this.score = 0L;
        this.isActive = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public boolean getIsActive() {
    	return isActive;
    }
    
    public void setIsActive(boolean isActive) {
    	this.isActive = isActive;
    }
}
