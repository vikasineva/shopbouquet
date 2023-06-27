package com.ua.rosella.controller;

import com.ua.rosella.AuthenticationResponse;
import com.ua.rosella.exceptions.ObjectNotValidException;
import com.ua.rosella.request.AuthenticationRequest;
import com.ua.rosella.request.RegisterRequest;
import com.ua.rosella.service.AuthenticationService;
import com.ua.rosella.service.UserService;
import com.ua.rosella.validators.ObjectsValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final AuthenticationService service;
    private final ObjectsValidator<RegisterRequest> registerValidator;
    private final ObjectsValidator<AuthenticationRequest> authenticationValidator;
    private final UserService userService;

    public LoginController(AuthenticationService service,
                           ObjectsValidator<RegisterRequest> registerValidator,
                           ObjectsValidator<AuthenticationRequest> authenticationValidator,
                           UserService userService) {
        this.service = service;
        this.registerValidator = registerValidator;
        this.authenticationValidator = authenticationValidator;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        registerValidator.validate(request);
        if(request.getBirthday() != null &&
                (request.getBirthday().after(new Date(111, Calendar.JANUARY, 1)) ||
                request.getBirthday().before(new Date(0, Calendar.JANUARY, 1)))) {
            throw new ObjectNotValidException(new HashSet<String>(List.of("Дата народження не відповідає умовам реєстрації")));
        }
        if(userService.getUserByUserEmail(request.getEmail()).isPresent())
            throw new ObjectNotValidException(new HashSet<String>(List.of("Користувач з такою поштою вже існує")));
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> auth(@RequestBody AuthenticationRequest request) {
        authenticationValidator.validate(request);
        if(userService.getUserByUserEmail(request.getUserEmail()).isEmpty())
            throw new ObjectNotValidException(new HashSet<String>(List.of("Користувача з такою поштою не існує")));
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        service.refreshToken(request, response);
    }
}
