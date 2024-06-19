package pl.chmielewski.LeavePlanner.Authentication.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import pl.chmielewski.LeavePlanner.Authentication.api.ApiResponse;
import pl.chmielewski.LeavePlanner.Authentication.request.LoginUserDTO;
import pl.chmielewski.LeavePlanner.Authentication.token.JwtService;
import pl.chmielewski.LeavePlanner.Authentication.token.TokenService;
import pl.chmielewski.LeavePlanner.Authentication.user.User;
import pl.chmielewski.LeavePlanner.Authentication.user.UserService;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenService tokenService;
    private final JwtService jwtService;


    @Autowired
    public AuthService(AuthenticationManager authenticationManager, UserService userService, TokenService tokenService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenService = tokenService;
        this.jwtService = jwtService;
    }

    public String login(LoginUserDTO loginUserDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDTO.email(),
                        loginUserDTO.password()
                ));
        User user = userService.getUserByEmail(loginUserDTO.email());
        tokenService.revokeAllUserTokens(user);
        String token = jwtService.generateToken(user);
        tokenService.saveTokenForUser(token, user);
        return token;
    }

    public ApiResponse register() {

    }
}