package com.devmobil.algamoney.api.exception;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

//@ControllerAdvice
public class AlgamoneyExceptionHandler extends ResponseEntityExceptionHandler{

	@Autowired
	private MessageSource messageSource;
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Error erro = new Error("Mensagem inválida");
		return super.handleExceptionInternal(ex, erro, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Error error = new Error("Argumento(s) de campo(s) inválido(s).", createListErrors(ex.getBindingResult()));
		return super.handleExceptionInternal(ex, error, headers, status, request);
	}
	
	
	
	private List<Error> createListErrors(BindingResult bindingResult) {
		List<Error> erros = new ArrayList<>();
		for(FieldError fieldError: bindingResult.getFieldErrors()) {
//			erros.add(new Error(fieldError.toString()));
			erros.add(new Error(messageSource.getMessage(fieldError, LocaleContextHolder.getLocale())) );
			
		}
		return erros;
	}



	@Getter
	@AllArgsConstructor
	public static class Error {
		public Error(String message) {
			this.message = message;
		}
		private String message;
		private List<Error> errors;
	}
}
