package scoreboard.exception.score;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Member's Team not present") //400
public class TeamNotPresentException extends Exception {
	private static final long serialVersionUID = -3332292346834123456L;

	public TeamNotPresentException(){
		super("Member's Team not present");
	}
}