package lucacampion.ProgettoFinaleSettimanale.Entitites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="events")
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Event {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private int id;
    private String nome;
    private LocalDate data;
    private int posti;

}
