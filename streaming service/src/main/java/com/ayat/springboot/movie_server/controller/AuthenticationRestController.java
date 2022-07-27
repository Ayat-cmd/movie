package com.ayat.springboot.movie_server.controller;

import com.ayat.springboot.movie_server.dao.UserRepository;
import com.ayat.springboot.movie_server.dto.AuthenticationRequestDto;
import com.ayat.springboot.movie_server.exception_handler.MessageResponse;
import com.ayat.springboot.movie_server.entity.Role;
import com.ayat.springboot.movie_server.entity.Status;
import com.ayat.springboot.movie_server.entity.UserEntity;
import com.ayat.springboot.movie_server.security.jwt.JwtTokenProvider;
import com.ayat.springboot.movie_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserService userService;

    public AuthenticationRestController(AuthenticationManager authenticationManager, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                    requestDto.getEmail(), requestDto.getPassword()));
            UserEntity userEntity = userRepository.findByEmail(requestDto.getEmail())
                    .orElseThrow(()->new UsernameNotFoundException("User doesn't exists"));
            String token = jwtTokenProvider.createToken(requestDto.getEmail(), userEntity.getRole().name());
            Map<Object, Object> response = new HashMap<>();
            response.put("email", requestDto.getEmail());
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody UserEntity userEntity) {
        if (userRepository.existsByEmail(userEntity.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is exists"));
        }
        userEntity.setRole(Role.USER);
        userEntity.setStatus(Status.ACTIVE);
        userService.saveOrUpdateUser(userEntity);
        return ResponseEntity.ok("Done");
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}
