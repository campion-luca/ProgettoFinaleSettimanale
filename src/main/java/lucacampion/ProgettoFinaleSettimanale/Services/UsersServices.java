package lucacampion.ProgettoFinaleSettimanale.Services;

import lucacampion.ProgettoFinaleSettimanale.Entitites.User;
import lucacampion.ProgettoFinaleSettimanale.exceptions.NotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class UsersServices {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder bcrypt;

    public User save(NewUserDTO body) {
        // 1. Verifico che l'email non sia già in uso
        this.usersRepository.findByEmail(body.email()).ifPresent(
                // 1.1 Se trovo uno user con quell'indirizzo triggera un errore
                user -> {
                    throw new BadRequestException("Email " + body.email() + " già in uso!");
                }
        );

        // 2. Se è ok allora aggiungo i campi "server-generated" come ad esempio avatarURL
        User newUser = new User(body.name(), body.surname(), body.email(), bcrypt.encode(body.password()),
                "https://ui-avatars.com/api/?name=" + body.name() + "+" + body.surname());

        // 3. Salvo il nuovo utente
        return this.usersRepository.save(newUser);
    }

    public Page<User> findAll(int page, int size, String sortBy) {
        if (size > 100) size = 100; // Limitiamo la size max a 100 così da client non possono inserire numeri troppo grandi
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        // Pageable ci consente di configurare come devono essere paginati i risultati passando numero di pagina, numero elementi pagina e criterio di ordinamento
        return this.usersRepository.findAll(pageable);
    }

    public User findById(UUID userId) {
        return this.usersRepository.findById(userId).orElseThrow(() -> new ChangeSetPersister.NotFoundException(userId));
    }


    public User findByEmail(String email) {
        return this.usersRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("L'utente con email " + email + " non è stato trovato"));
    }


}
