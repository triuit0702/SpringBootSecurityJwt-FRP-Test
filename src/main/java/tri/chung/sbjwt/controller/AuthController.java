package tri.chung.sbjwt.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tri.chung.sbjwt.constant.Constant;
import tri.chung.sbjwt.model.JwtAuthenticationResponse;
import tri.chung.sbjwt.model.LoginRequest;
import tri.chung.sbjwt.security.CustomUserDetails;
import tri.chung.sbjwt.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;


	@GetMapping("/index")
	public String index() {
		return "<html><h1>Chao cac ban</h1></html>";
	}

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		String jwt = StringUtils.EMPTY;
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
			jwt = tokenProvider.generateToken(customUserDetails.getId());
		} catch (DisabledException e) {
			return ResponseEntity.badRequest().body(new JwtAuthenticationResponse(jwt, Constant.JWT_USER_DISABLED));
		} catch (BadCredentialsException e) {
			return ResponseEntity.badRequest().body(new JwtAuthenticationResponse(jwt, Constant.JWT_INVALID_CREDENTIALS));
		}
		
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, Constant.JWT_SUCCESS));
	}

}
