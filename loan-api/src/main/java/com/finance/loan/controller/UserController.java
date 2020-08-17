package com.finance.loan.controller;


import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finance.loan.model.User;
import com.finance.loan.repository.RoleRepository;
import com.finance.loan.repository.UserRepository;
import com.finance.loan.service.impl.UserDetailsImpl;
import com.finance.loan.service.impl.UserServiceImpl;
import com.finance.loan.util.JwtUtils;
import com.finance.loan.vo.AuthResponseVO;
import com.finance.loan.vo.LoginRequestVO;
import com.finance.loan.vo.MessageResponseVO;
import com.finance.loan.vo.RegisterRequestVO;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	UserServiceImpl userServiceImpl;


	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestVO loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new AuthResponseVO(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}
	
	@GetMapping(path="/signin")
	@CrossOrigin(origins = "${URL}")
	public ResponseEntity<User> getUser(@RequestParam String username, @RequestParam String password) {
		
		return new ResponseEntity<User>(userServiceImpl.findByUserNameAndPassWord(username, password), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/register")
	@CrossOrigin(origins = "${URL}")
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequestVO registerRequest) {
		if (userRepository.existsByUsername(registerRequest.getUserName())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponseVO("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(registerRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponseVO("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(registerRequest.getUserName(), 
				registerRequest.getEmail(),
				registerRequest.getPassword(),
				registerRequest.getFirstName(),
				registerRequest.getLastName(),
				registerRequest.getPhoneNumber(),
				registerRequest.getRole());
				userRepository.save(user);

		return ResponseEntity.ok(new MessageResponseVO("User registered successfully!"));
	}
}
