package scoreboard.exception.member;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Member's Team already present") //400
public class TeamAlreadyPresentException extends Exception {
	private static final long serialVersionUID = -3332292346834123654L;

	public TeamAlreadyPresentException(){
		super("Member's Team already present");
	}
}