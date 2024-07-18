package com.authentication.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ProblemDetail handleSecurityException(Exception exception) {

		ProblemDetail problemDetail = null;

		if (exception instanceof BadCredentialsException) {
			problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), exception.getMessage());
			problemDetail.setProperty("description", "The username or password is incorrect");

			return problemDetail;
		}

		if (exception instanceof AccountStatusException) {
			problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
			problemDetail.setProperty("description", "The account is locked");
		}

		if (exception instanceof AccessDeniedException) {
			problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
			problemDetail.setProperty("description", "You are not authorized to access this resource");
		}

		if (exception instanceof SignatureException) {
			problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
			problemDetail.setProperty("description", "The JWT signature is invalid");
		}

		if (exception instanceof ExpiredJwtException) {
			problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
			problemDetail.setProperty("description", "The JWT token has expired");
		}

		if (problemDetail == null) {
			problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), exception.getMessage());
			problemDetail.setProperty("description", "Unknown internal server error.");
		}

		return problemDetail;

	}

}
