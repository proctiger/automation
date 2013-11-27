package uol.affiliated.commons.test.exception;

public class StepException extends Exception {

	private static final long serialVersionUID = 2091325470742896721L;

	public StepException(Throwable t) {
		super(t);
	}
	
	public String getExceptionMessage() {
		return getCause().getLocalizedMessage();
	}
}
