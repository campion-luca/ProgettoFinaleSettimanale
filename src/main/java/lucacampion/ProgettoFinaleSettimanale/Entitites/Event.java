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
@NoArgsConstructor
@ToString
public class Event {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private int id;
    private String nome;
    private LocalDate data;
    private int posti;
    private String luogo;
    private String descrizione;


    public Event(LocalDate data, String descrizione, String luogo, String nome, int posti) {
        this.data = data;
        this.descrizione = descrizione;
        this.luogo = luogo;
        this.nome = nome;
        this.posti = posti;
    }
}
