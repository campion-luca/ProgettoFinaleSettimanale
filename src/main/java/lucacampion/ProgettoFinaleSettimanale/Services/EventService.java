package lucacampion.ProgettoFinaleSettimanale.Services;

import lucacampion.ProgettoFinaleSettimanale.Entitites.Event;
import lucacampion.ProgettoFinaleSettimanale.Entitites.User;
import lucacampion.ProgettoFinaleSettimanale.exceptions.NotFoundException;
import lucacampion.ProgettoFinaleSettimanale.exceptions.PowerLimitException;
import lucacampion.ProgettoFinaleSettimanale.payloads.NewEventDTO;
import lucacampion.ProgettoFinaleSettimanale.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    UsersServices usersServices;

    public Event createEvent(NewEventDTO body){
        return eventRepository.save(
                new Event(body.titolo(),
                        body.descrizione(),
                        body.luogo(),
                        body.posti(),
                        LocalDate.parse(body.data()))
        );
    }

    public List<Event> vediTuttiGliEventi(){
        return eventRepository.findAll();
    }
    public Event findById(int id){
        Event found = null;
        found = eventRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return found;
    }
    public Event modifyEvent(int id, NewEventDTO body, User currentUser){
        Event eventModif = findById(id);
            eventModif.setNome(body.titolo());
            eventModif.setDescrizione(body.descrizione());
            eventModif.setData(LocalDate.parse(body.data()));
            eventModif.setLuogo(body.luogo());
        return eventRepository.save(eventModif);
    }
    public List<Event> mineListEvent(User currentUtente){
        return eventRepository.findByUser(currentUtente);
    }

}
