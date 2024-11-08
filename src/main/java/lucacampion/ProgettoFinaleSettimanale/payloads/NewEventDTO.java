package lucacampion.ProgettoFinaleSettimanale.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewEventDTO(@NotEmpty(message = "Titolo obbligatorio")
                          @Size(min = 3 ,max = 20, message = "Il titolo deve essere compreso fra 3 e 20 caratteri")
                          String titolo,
                          @NotEmpty(message = "Descrizione obbligatoria")
                          @Size(min = 3 ,max = 20, message = "La descrizione deve essere compresa fra 3 e 20 caratteri")
                          String descrizione,
                          @NotEmpty(message = "Luogo obbligatorio")
                          @Size(min = 3 ,max = 20, message = "Il luogo deve essere compreso fra 3 e 20 caratteri")
                          String luogo,
                          Integer posti,
                          @NotEmpty(message = "Data obbligatoria")
                          String data) {
}
