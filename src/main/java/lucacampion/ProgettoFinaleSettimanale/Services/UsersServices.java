package lucacampion.ProgettoFinaleSettimanale.Services;

import lucacampion.ProgettoFinaleSettimanale.Entitites.User;
import lucacampion.ProgettoFinaleSettimanale.exceptions.ExistingUserException;
import lucacampion.ProgettoFinaleSettimanale.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UsersServices {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder bcrypt;

    public User findById(int id){
        User found = null;
        found = usersRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        return found;
    }

    public User saveUser(NewUtenteDTO body){
        if(usersRepository.findById(body.username()).isPresent()){
            throw new ExistingUserException(body.username());
        }
        return usersRepository.save(new Utente(body.username(), body.nome(), body.cognome(), bcrypt.encode(body.password())));

    }

}
