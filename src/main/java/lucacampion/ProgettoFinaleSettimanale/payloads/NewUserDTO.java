package lucacampion.ProgettoFinaleSettimanale.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewUserDTO(@NotEmpty(message = "Username obbligatorio")
                         @Size(min = 3 ,max = 20, message = "L'username deve essere compreso fra 3 e 20 caratteri")
                         String username,
                         @NotEmpty(message = "Il nome è obbligatorio")
                         @Size(min = 3 ,max = 20, message = "Il nome deve essere compreso fra 3 e 20 caratteri")
                         String nome,
                         @NotEmpty(message = "Cognome obbligatorio")
                         @Size(min = 3 ,max = 20, message = "Il cognome deve essere compreso fra 3 e 20 caratteri")
                         String cognome,
                         @NotEmpty(message = "Password obbligatoria")
                         String password) {
}
