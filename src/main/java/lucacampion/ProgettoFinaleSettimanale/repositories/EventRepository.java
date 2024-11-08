package lucacampion.ProgettoFinaleSettimanale.repositories;

import lucacampion.ProgettoFinaleSettimanale.Entitites.Event;
import lucacampion.ProgettoFinaleSettimanale.Entitites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findByUser(User user);
}
