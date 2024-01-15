package exceptions;

public class PersonAlreadyExists extends Exception {
	private static final long serialVersionUID = 1L;

	public PersonAlreadyExists(String message){
		super(message);
	}
}

