package com.devmobil.algamoney.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@AllArgsConstructor
@Getter
public class ErrorResponse {
	private final String message;
    private final int code;
    private final String status;
    private final String objectName;
    private final List<ErrorObject> errors;   
}