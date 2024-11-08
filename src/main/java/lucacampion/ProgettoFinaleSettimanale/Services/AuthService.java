package lucacampion.ProgettoFinaleSettimanale.Services;

import lucacampion.ProgettoFinaleSettimanale.Entitites.User;
import lucacampion.ProgettoFinaleSettimanale.exceptions.UnauthorizedException;
import lucacampion.ProgettoFinaleSettimanale.payloads.LoginDTO;
import lucacampion.ProgettoFinaleSettimanale.tools.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private JWT jwt;
    @Autowired
    private UsersServices usersServices;
    @Autowired
    private PasswordEncoder bcrypt;

    public String checkCredentialAndResponseToken(LoginDTO body) {
        User found = usersServices.findByUsername(body.username());
        if (bcrypt.matches(body.password(), found.getPassword())) {
            String accesToken = jwt.createToken(found);
            return accesToken;
        } else throw new UnauthorizedException("Credenziali errate! riprova o chiedi l'invio dell'email");
    }
}
