package lucacampion.ProgettoFinaleSettimanale.Entitites;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User implements UserDetails {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private int id;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String nome;
    private String cognome;
    private String username;
    private String email;
    private String password;


    public User(String cognome, String email, String nome, String password, String username) {
        this.cognome = cognome;
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.role = Role.USER;
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }
}