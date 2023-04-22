package racingcar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WebControllerAdvice {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleException(final RuntimeException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(exception.getMessage());

	}
}
