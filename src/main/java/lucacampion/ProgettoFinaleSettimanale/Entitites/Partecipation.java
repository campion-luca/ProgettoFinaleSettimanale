package lucacampion.ProgettoFinaleSettimanale.Entitites;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="partecipations")
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Partecipation {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private int id;

    @ManyToOne
    @JoinColumn(name="utente_id")
    private User utente;

    @ManyToOne
    @JoinColumn(name="evento_id")
    private Event evento;
}
