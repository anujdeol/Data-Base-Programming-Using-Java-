package exceptions;

public class OutOflimitException extends Exception {

	private static final long serialVersionUID = 1L;

	public OutOflimitException(String message){
		super(message);
	}
}
