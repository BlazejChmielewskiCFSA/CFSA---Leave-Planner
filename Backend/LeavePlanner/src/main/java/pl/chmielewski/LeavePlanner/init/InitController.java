package pl.chmielewski.LeavePlanner.init;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.chmielewski.LeavePlanner.Authentication.api.response.UserRegisterSuccessedDTO;
import pl.chmielewski.LeavePlanner.Authentication.auth.AuthService;
import pl.chmielewski.LeavePlanner.Authentication.api.request.RegisterUserDTO;

@RestController
@RequestMapping("/auth/initialize")
public class InitController {

    private final AuthService authService;

    @Autowired
    public InitController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void initialize(HttpServletResponse response) {
        RegisterUserDTO registerUserDTO = new RegisterUserDTO(
                "Błażej",
                "Chmielewski",
                "blazejchmielewski01@gmail.com",
                "haslo123"
        );
        RegisterUserDTO registerUserDTO2 = new RegisterUserDTO(
                "Adam",
                "Adamowski",
                "blazej13032000@wp.pl",
                "haslo123"
        );
        try {
            UserRegisterSuccessedDTO register = authService.register(registerUserDTO, response);
            UserRegisterSuccessedDTO register2 = authService.register(registerUserDTO2, response);
            authService.enableUser(register.uuid());
            authService.enableUser(register2.uuid());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}