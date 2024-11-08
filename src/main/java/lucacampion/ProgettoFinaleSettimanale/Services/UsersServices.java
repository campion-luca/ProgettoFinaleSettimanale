package lucacampion.ProgettoFinaleSettimanale.Services;

import lucacampion.ProgettoFinaleSettimanale.Entitites.User;
import lucacampion.ProgettoFinaleSettimanale.exceptions.NotFoundException;
import lucacampion.ProgettoFinaleSettimanale.payloads.NewUserDTO;
import lucacampion.ProgettoFinaleSettimanale.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UsersServices {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcrypt;

    public User findById(int userId){
        return this.userRepository.findById(userId).orElseThrow(()-> new NotFoundException(userId));
    }


    public User saveUser(NewUserDTO body) {
        User newUser = new User(body.nome(), body.cognome(), body.email(), bcrypt.encode(body.password()),body.username());
        return this.userRepository.save(newUser);
    }


}
