package com.authentication.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.authentication.Dto.LoginUserDto;
import com.authentication.Dto.RegisterUserDto;
import com.authentication.entities.User;
import com.authentication.repositories.UserRepositories;

@Service
public class AuthenticationService {
	
	private final PasswordEncoder passwordEncoder;
	private final UserRepositories userRepositories;
	private final AuthenticationManager authenticationManager;

	public AuthenticationService(PasswordEncoder passwordEncoder, UserRepositories userRepositories,
			AuthenticationManager authenticationManager) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userRepositories = userRepositories;
		this.authenticationManager = authenticationManager;
	}



	public User signup(RegisterUserDto input) {
		User user = new User();
		user.setEmail(input.getEmail());
		user.setPassword(passwordEncoder.encode(input.getPassword()));
		user.setFullName(input.getFullName());
		return userRepositories.save(user);
	}
	
	public User authenticate(LoginUserDto input) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));
		return userRepositories.findByEmail(input.getEmail()).orElseThrow();
	}

}
