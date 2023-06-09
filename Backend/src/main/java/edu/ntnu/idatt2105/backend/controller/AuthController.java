package edu.ntnu.idatt2105.backend.controller;

import edu.ntnu.idatt2105.backend.DTO.AuthenticationRequest;
import edu.ntnu.idatt2105.backend.DTO.AuthenticationResponse;
import edu.ntnu.idatt2105.backend.security.AuthenticationService;
import edu.ntnu.idatt2105.backend.DTO.RegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for user authentication. The user can register and login.
 *
 * @author Brage H. Kvamme
 * @version 1.0
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    Logger logger = org.slf4j.LoggerFactory.getLogger(AuthController.class);
    private final AuthenticationService authenticationService;

    /**
     * Register a new user. The user will be assigned the role USER. The user will be
     * authenticated and a JWT token will be returned. The token will be valid for 60
     * minutes.
     *
     * @param request The request body containing the user information.
     * @return A response containing the JWT token.
     */
    @Operation(summary = "Register a new user", description = "The user will be assigned the role USER. " +
            "The user will be authenticated and a JWT token will be returned. The token will be valid for 60 minutes.")
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        try {
            return ResponseEntity.ok(authenticationService.register(request));
        } catch (Exception e) {
            logger.error("Error registering user", e);
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Authenticate a user. The user will be authenticated and a JWT token will be
     * returned. The token will be valid for 60 minutes.
     *
     * @param request The request body containing the email and password.
     * @return A response containing the JWT token.
     */
    @Operation(summary = "Authenticate a user", description = "The user will be authenticated and a JWT token " +
            "will be returned. The token will be valid for 60 minutes.")
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
