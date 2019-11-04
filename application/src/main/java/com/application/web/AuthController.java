package com.application.web;

import com.account.domain.User;
import com.account.service.UserService;
import com.application.facade.UserAuthFacade;
import com.utility.web.request.user.LoginRequest;
import com.utility.web.request.user.PasswordReset;
import com.utility.web.request.user.SignUpRequest;
import com.utility.web.response.ApiResponse;
import com.utility.web.response.user.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private UserService userService;
    private UserAuthFacade userAuthFacade;

    @Autowired
    public AuthController(UserService userService,
                          UserAuthFacade userAuthFacade) {
        this.userService = userService;
        this.userAuthFacade = userAuthFacade;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userAuthFacade.authenticateUser(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        User user = userService.registerUser(signUpRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully@"));
    }


    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPasswordToken(@RequestParam String email) {

        return ResponseEntity.ok(userAuthFacade.generatePasswordResetToken(email));
    }

    @PostMapping("/reset-password")
    public ApiResponse resetPassword(@RequestParam String token,
                                     @RequestBody PasswordReset passwordReset) {

        return userAuthFacade.resetPassword(token, passwordReset);
    }
}