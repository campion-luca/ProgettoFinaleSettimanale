package lucacampion.ProgettoFinaleSettimanale.Services;

import lucacampion.ProgettoFinaleSettimanale.Entitites.User;
import lucacampion.ProgettoFinaleSettimanale.exceptions.BadRequestException;
import lucacampion.ProgettoFinaleSettimanale.exceptions.NotFoundException;
import lucacampion.ProgettoFinaleSettimanale.payloads.NewUserDTO;
import lucacampion.ProgettoFinaleSettimanale.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UsersServices {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcrypt;

    // FIND
    public User findById(int userId){
        return this.userRepository.findById(userId).orElseThrow(()-> new NotFoundException(userId));
    }

    // SAVE USER
    public User saveUser(NewUserDTO body) {
        User newUser = new User(body.nome(), body.cognome(), body.email(), bcrypt.encode(body.password()),body.username());
        return this.userRepository.save(newUser);
    }

    // FIND ALL
    public Page<User> findAll(int page, int size, String sortBy) {
        if (size > 100) size = 100; // Limitiamo la size max a 100 così da client non possono inserire numeri troppo grandi
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        // Pageable ci consente di configurare come devono essere paginati i risultati passando numero di pagina, numero elementi pagina e criterio di ordinamento
        return this.userRepository.findAll(pageable);
    }

    // FIND AND UPDATE
    public User findByIdAndUpdate(int userId, NewUserDTO body) {
        User found = this.findById(userId);
        if (!found.getEmail().equals(body.email())) {
            this.userRepository.findByEmail(body.email()).ifPresent(
                    user -> {
                        throw new BadRequestException("Email " + body.email() + " già in uso!");
                    }
            );
        }
        found.setUsername(body.username());
        found.setEmail(body.email());
        found.setPassword(body.password());
        found.setNome(body.nome());
        found.setCognome(body.cognome());
        return this.userRepository.save(found);
    }

    // FIND AND DELETE
    public void findByIdAndDelete(int userId) {
        User found = this.findById(userId);
        this.userRepository.delete(found);
    }

    // FIND BY MAIL
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("L'utente con email " + email + " non è stato trovato"));
    }


}
