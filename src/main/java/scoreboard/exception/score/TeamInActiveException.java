package scoreboard.exception.score;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Member's Team inactive") //400
public class TeamInActiveException extends Exception {
	private static final long serialVersionUID = -3332292346834654321L;

	public TeamInActiveException(){
		super("Member's Team inactive");
	}
}