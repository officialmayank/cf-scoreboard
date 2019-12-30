package scoreboard.exception.member;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Team not active") //400
public class TeamInActiveException extends Exception {
	private static final long serialVersionUID = -3332292346834111333L;

	public TeamInActiveException(){
		super("Team not active");
	}
}