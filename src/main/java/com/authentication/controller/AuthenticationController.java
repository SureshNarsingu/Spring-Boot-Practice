package com.authentication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.Dto.LoginUserDto;
import com.authentication.Dto.RegisterUserDto;
import com.authentication.entities.User;
import com.authentication.response.LoginResponse;
import com.authentication.service.AuthenticationService;
import com.authentication.service.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	private final JwtService jwtService;
	private final AuthenticationService authenticationService;

	public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
		this.jwtService = jwtService;
		this.authenticationService = authenticationService;
	}

	@PostMapping("/signup")
	public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
		User authenticatedUser = authenticationService.signup(registerUserDto);
		return new ResponseEntity<>(authenticatedUser, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginUserDto loginUserDto) {
		User authenticate = authenticationService.authenticate(loginUserDto);
		String token = jwtService.generateToken(authenticate);
		LoginResponse loginResponse = new LoginResponse().setToken(token).setExpiresIn(jwtService.getExpirationTime());
		return new ResponseEntity<>(loginResponse, HttpStatus.CREATED);
	}

}
