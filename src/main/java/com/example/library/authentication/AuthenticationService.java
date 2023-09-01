package com.example.library.authentication;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.library.config.JwtService;
import com.example.library.exception.UserRoleNotFoundException;
import com.example.library.model.Role;
import com.example.library.model.User;
import com.example.library.model.UserRole;
import com.example.library.repository.UserRepository;
import com.example.library.repository.UserRoleRepository;
import com.example.library.token.Token;
import com.example.library.token.TokenRepository;
import com.example.library.token.TokenType;

@Service
public class AuthenticationService {

	private final UserRepository repository;
	private final TokenRepository tokenRepository;
	private final UserRoleRepository userRoleRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	public AuthenticationService(UserRepository repository, UserRoleRepository userRoleRepository,
			PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager,
			TokenRepository tokenRepository) {

		this.repository = repository;
		this.userRoleRepository = userRoleRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
		this.tokenRepository = tokenRepository;
	}

	public AuthenticationResponse register(RegisterRequest request) {
		
		var user = User.builder().username(request.getUsername()).email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword())).role(Role.USER).build();

		var savedUser = repository.save(user);

		var jwtToken = jwtService.generateToken(user);
		
		saveUserToken(savedUser, jwtToken);

		return AuthenticationResponse.builder().token(jwtToken).build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {

		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		var user = repository.findByUsername(request.getUsername()).orElseThrow();

		var jwtToken = jwtService.generateToken(user);
		
		revokeAllUserTokens(user);
		
		saveUserToken(user, jwtToken);

		return AuthenticationResponse.builder().token(jwtToken).build();
	}
	
	private void revokeAllUserTokens(User user) {
		
		var validUserTokens = tokenRepository.findAllValidTokensByUser(user.getId());
		if (validUserTokens.isEmpty()) 
			return;
		validUserTokens.forEach(token -> {
			token.setExpired(true);
			token.setRevoked(true);
		});
		tokenRepository.saveAll(validUserTokens);
	}
	
	private void saveUserToken(User user, String jwtToken) {
		var token = Token.builder().user(user).token(jwtToken).tokenType(TokenType.BEARER).expired(false)
				.revoked(false).build();
		tokenRepository.save(token);
	}
}
