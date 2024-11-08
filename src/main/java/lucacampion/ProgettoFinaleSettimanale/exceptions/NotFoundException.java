package lucacampion.ProgettoFinaleSettimanale.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(int id) {
        super("L'id " + id + " non è stato trovato");
    }

    public NotFoundException(String msg) {
        super(msg);
    }
}
