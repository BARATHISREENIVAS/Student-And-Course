package in.sreenu.studentandcourseonetomany.exception;

import java.net.http.HttpHeaders;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<CustomExceptionResponse> notFoundException(NotFoundException nfe, WebRequest wr) {
		CustomExceptionResponse response = new CustomExceptionResponse();
		response.setDate(new Date());
		response.setDescription(wr.getDescription(false));
		response.setMessage(nfe.getMessage());
		return new ResponseEntity<CustomExceptionResponse>(response, HttpStatus.BAD_REQUEST);

	}

	
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//			HttpHeaders header, HttpStatusCode status, WebRequest wr) {
//		Map<String, String> error = new HashMap();
//		ex.getBindingResult().getAllErrors().forEach((errors) -> {
//			String fieldName = ((FieldError) errors).getField();
//			String message = errors.getDefaultMessage();
//			error.put(fieldName, message);
//		});
//		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//
//	}
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, String> error = new HashMap();
		ex.getBindingResult().getAllErrors().forEach((errors) -> {
			String fieldName = ((FieldError) errors).getField();
			String message = errors.getDefaultMessage();
			error.put(fieldName, message);
		});
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}

}
