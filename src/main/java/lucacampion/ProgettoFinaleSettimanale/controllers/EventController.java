package lucacampion.ProgettoFinaleSettimanale.controllers;

import lucacampion.ProgettoFinaleSettimanale.Entitites.Event;
import lucacampion.ProgettoFinaleSettimanale.Entitites.User;
import lucacampion.ProgettoFinaleSettimanale.Services.EventService;
import lucacampion.ProgettoFinaleSettimanale.exceptions.BadRequestException;
import lucacampion.ProgettoFinaleSettimanale.payloads.NewEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class EventController {
    @Autowired
    EventService eventService;

    @PostMapping
    @PreAuthorize("hasAuthority('ORGANIZZATORE_EVENTI')")
    public Event addEvent(@AuthenticationPrincipal User currentOrganizzatore, @RequestBody @Validated NewEventDTO body, BindingResult validationResult){
        if(validationResult.hasErrors()){
            String message = validationResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException(message);
        }
        return eventService.createEvent(currentOrganizzatore, body);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ORGANIZZATORE_EVENTI')")
    public List<Event> showEvent(){
        return eventService.vediTuttiGliEventi();
    }



    @PutMapping("/{idEvent}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE_EVENTI')")
    public Event modifyEvent(@PathVariable int idEvento, @RequestBody NewEventDTO body, @AuthenticationPrincipal User currentOrganizzatore){
        return  eventService.modifyEvent(idEvento, body, currentOrganizzatore);
    }


    // io
    @GetMapping("/profile")
    @PreAuthorize("hasAuthority('ORGANIZZATORE_EVENTI')")
    public List<Event> showMyEvent(@AuthenticationPrincipal User currentOrganizzatore){
        return eventService.mineListEvent(currentOrganizzatore);
    }

}
