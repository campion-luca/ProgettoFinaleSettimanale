package lucacampion.ProgettoFinaleSettimanale.repositories;

import lucacampion.ProgettoFinaleSettimanale.Entitites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
