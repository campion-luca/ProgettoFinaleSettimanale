package lucacampion.ProgettoFinaleSettimanale.exceptions;

public class PowerLimitException extends RuntimeException {
    public PowerLimitException(String currentSurname, String username) {
        super(currentSurname + " non hai il permesso di modificare l'evento di " + username);
    }
}
