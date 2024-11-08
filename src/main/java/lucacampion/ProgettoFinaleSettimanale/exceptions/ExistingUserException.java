package lucacampion.ProgettoFinaleSettimanale.exceptions;

public class ExistingUserException extends RuntimeException {
  public ExistingUserException(String username) {
    super(
            username + " già presente, riprova"
    );
  }
}
