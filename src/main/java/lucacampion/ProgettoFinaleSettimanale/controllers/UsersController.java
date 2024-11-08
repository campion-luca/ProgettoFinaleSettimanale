package lucacampion.ProgettoFinaleSettimanale.controllers;

import lucacampion.ProgettoFinaleSettimanale.Entitites.User;
import lucacampion.ProgettoFinaleSettimanale.Services.UsersServices;
import lucacampion.ProgettoFinaleSettimanale.exceptions.BadRequestException;
import lucacampion.ProgettoFinaleSettimanale.payloads.NewUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersServices usersServices;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')") // <-- Solo gli ADMIN possono visualizzare la lista degli utenti in questa app
    public Page<User> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                              @RequestParam(defaultValue = "id") String sortBy) {
        // Mettiamo dei valori di default per far si che non ci siano errori se il client non ci invia uno dei query parameters
        return this.usersServices.findAll(page, size, sortBy);
    }

    @GetMapping("/me")
    public User getProfile(@AuthenticationPrincipal User currentAuthenticatedUser) {
        return currentAuthenticatedUser;
    }

    @PutMapping("/me")
    public User updateProfile(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestBody @Validated NewUserDTO body) {
        return this.usersServices.findByIdAndUpdate(currentAuthenticatedUser.getId(), body);
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@AuthenticationPrincipal User currentAuthenticatedUser) {
        this.usersServices.findByIdAndDelete(currentAuthenticatedUser.getId());
    }

    @GetMapping("/{userId}")
    public User findById(@PathVariable int userId) {
        return this.usersServices.findById(userId);
    }


    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User findByIdAndUpdate(@PathVariable int userId, @RequestBody @Validated NewUserDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            validationResult.getAllErrors().forEach(System.out::println);
            throw new BadRequestException("PAYLOAD ERROR");
        }
        return this.usersServices.findByIdAndUpdate(userId, body);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable int userId) {
        this.usersServices.findByIdAndDelete(userId);
    }

}
