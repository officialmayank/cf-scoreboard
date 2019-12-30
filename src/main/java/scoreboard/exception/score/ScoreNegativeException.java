package scoreboard.exception.score;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Score cannot be negative") //400
public class ScoreNegativeException extends Exception {
	private static final long serialVersionUID = -3332292346834265371L;

	public ScoreNegativeException(){
		super("Score cannot be negative");
	}
}
