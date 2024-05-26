package savitarna.siuntusavitarna.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import savitarna.siuntusavitarna.dtos.LoginUserDto;
import savitarna.siuntusavitarna.dtos.RegisterUserDto;
import savitarna.siuntusavitarna.model.User;
import savitarna.siuntusavitarna.responses.LoginResponse;
import savitarna.siuntusavitarna.service.AuthenticationService;
import savitarna.siuntusavitarna.service.JwtService;

import org.springframework.http.ResponseEntity;

@CrossOrigin
@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Boolean> register(@RequestBody RegisterUserDto registerUserDto) {
        try
        {
            authenticationService.signup(registerUserDto);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(false);
        }
        return ResponseEntity.ok(true);
    }

    @PostMapping("/createKioskUser")
    public ResponseEntity<Boolean> createKioskUser(@RequestBody RegisterUserDto registerUserDto) {
        try
        {
            authenticationService.createKioskUser(registerUserDto);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(false);
        }
        return ResponseEntity.ok(true);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setUserId(authenticatedUser.getId());
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}