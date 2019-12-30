package scoreboard.exception.member;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Team not present") //400
public class TeamNotPresentException extends Exception {
	private static final long serialVersionUID = -3332292346834999888L;

	public TeamNotPresentException(){
		super("Team not present");
	}
}