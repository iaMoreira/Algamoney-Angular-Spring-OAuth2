package com.devmobil.algamoney.api.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// fonte: https://medium.com/@msealvial/validando-requisi%C3%A7%C3%B5es-e-tratando-exce%C3%A7%C3%B5es-no-spring-boot-1750ddb1e1cc
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request) {
		 List<ErrorObject> errors = getErrors(ex);
        ErrorResponse errorResponse = getErrorResponse(ex, status, errors);
        return new ResponseEntity<>(errorResponse, status);
	}

    private ErrorResponse getErrorResponse(MethodArgumentNotValidException ex, HttpStatus status, List<ErrorObject> errors) {
        return new ErrorResponse("Requisição possui campos inválidos", status.value(),
                status.getReasonPhrase(), ex.getBindingResult().getObjectName(), errors);
    }

    private List<ErrorObject> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorObject(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
                .collect(Collectors.toList());
    }
}
