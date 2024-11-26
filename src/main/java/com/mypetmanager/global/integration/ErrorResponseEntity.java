package com.mypetmanager.global.integration;

import org.springframework.http.ResponseEntity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponseEntity {

	private String code;
	private String message;

	public static ResponseEntity<ErrorResponseEntity> toResponseEntity(String code, String message,
		org.springframework.http.HttpStatus status) {
		return ResponseEntity.status(status).body(ErrorResponseEntity.builder().code(code).message(message).build());
	}

	@Builder
	public ErrorResponseEntity(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
