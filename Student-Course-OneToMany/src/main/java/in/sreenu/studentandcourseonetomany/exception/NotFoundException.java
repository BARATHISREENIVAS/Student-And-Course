package in.sreenu.studentandcourseonetomany.exception;

public class NotFoundException extends RuntimeException {

	private String fieldName;
	private String fieldValue;
	private String courseName;
	public NotFoundException(String fieldName, String fieldValue, String courseName) {
		super();
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		this.courseName = courseName;
	}

	public NotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
