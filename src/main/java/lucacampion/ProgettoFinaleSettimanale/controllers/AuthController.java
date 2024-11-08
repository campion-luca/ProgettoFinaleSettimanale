package lucacampion.ProgettoFinaleSettimanale.controllers;

import lucacampion.ProgettoFinaleSettimanale.Entitites.User;
import lucacampion.ProgettoFinaleSettimanale.Services.UsersServices;
import lucacampion.ProgettoFinaleSettimanale.exceptions.BadRequestException;
import lucacampion.ProgettoFinaleSettimanale.payloads.LoginDTO;
import lucacampion.ProgettoFinaleSettimanale.payloads.LoginResponseDTO;
import lucacampion.ProgettoFinaleSettimanale.payloads.NewUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    UsersServices usersServices;

    @PostMapping("/login")
    public LoginResponseDTO loginUtente(@RequestBody LoginDTO body){
        return new LoginResponseDTO(authService.checkCredentialAndResponseToken(body));
    }

    @PostMapping("/register")
    public User registraUtente(@RequestBody @Validated NewUserDTO body, BindingResult validationResult){
        if(validationResult.hasErrors()){
            String message = validationResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException(message);
        }
        return usersServices.saveUser(body);
    }
}
